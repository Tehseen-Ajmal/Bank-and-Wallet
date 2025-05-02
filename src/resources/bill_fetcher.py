from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.edge.service import Service
from selenium.webdriver.edge.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException  # Add this import
import sys  # For command-line arguments

# Initialize Edge WebDriver in headless mode
options = Options()
# options.headless = True
# options.add_argument('--headless')  # Additional argument to force headless mode
# options.add_argument('--disable-gpu')
# options.add_argument('--disable-software-rasterizer')
options.use_chromium = True  # Ensure Chromium-based Edge is used

# Path to msedgedriver.exe
msedgedriver_path = 'D:/msedgedriver.exe'
service = Service(executable_path=msedgedriver_path)

driver = webdriver.Edge(service=service, options=options)

def wait_for_element_and_send_keys(locator_type, locator_value, text):
    element = WebDriverWait(driver, 20).until(
        EC.presence_of_element_located((locator_type, locator_value))
    )
    element.send_keys(text)

def open_check_bill_page(ref_number):
    url = "http://www.lesco.gov.pk:36269/Modules/CustomerBillN/CheckBill.asp"
    driver.get(url)

    wait_for_element_and_send_keys(By.NAME, 'txtBatchNo', ref_number.split('-')[0])
    wait_for_element_and_send_keys(By.NAME, 'txtSubDiv', ref_number.split('-')[1])
    wait_for_element_and_send_keys(By.NAME, 'txtRefNo', ref_number.split('-')[2])

    dropdown = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.NAME, 'cmbRU'))
    )
    if 'r'==ref_number[-1].lower():
        dropdown.send_keys('R')
    else:
        dropdown.send_keys('U')

    button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.NAME, 'btnViewMenu'))
    )
    button.click()
    try:
        # Wait for the correct button to become clickable
        try:
            # Try finding the button for 'AccountStatus.aspx'
            button = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable(
                    (By.XPATH, "//form[@class='inline' and @action='AccountStatus.aspx']//button[@name='submit_param']")
                )
            )
        except TimeoutException:
            try:
                # If not found, try finding the button for 'AccountStatusMDI.aspx'
                button = WebDriverWait(driver, 10).until(
                    EC.element_to_be_clickable(
                        (By.XPATH,
                         "//form[@class='inline' and @action='AccountStatusMDI.aspx']//button[@name='submit_param']")
                    )
                )
            except TimeoutException:
                raise Exception("Both AccountStatus.aspx and AccountStatusMDI.aspx buttons were not found.")

        # Scroll into view and click the button using JavaScript
        driver.execute_script("arguments[0].scrollIntoView({block: 'center'});", button)
        driver.execute_script("arguments[0].click();", button)  # Click using JavaScript

        try:
            customer_name = driver.find_element(
                By.XPATH,
                "//div[@class='col-sm']/h5[normalize-space(text())='Customer Name:']/following-sibling::strong"
            ).text.strip()

            amount_within_due_date = driver.find_element(
                By.XPATH,
                "//div[@class='col-sm']/h5[normalize-space(text())='Amount Payable Within Due Date:']/following-sibling::strong"
            ).text.strip()

            amount_after_due_date = driver.find_element(
                By.XPATH,
                "//div[@class='col-sm']/h5[normalize-space(text())='Amount Payable After Due Date:']/following-sibling::strong"
            ).text.strip()

            due_date = driver.find_element(
                By.XPATH,
                "//div[@class='col-sm']/h5[normalize-space(text())='Due Date:']/following-sibling::strong"
            ).text.strip()

            return f"{customer_name}|{amount_within_due_date}|{amount_after_due_date}|{due_date}"

        except Exception as e:
            return f"Error extracting bill details: {e}"

    except TimeoutException as e:
        return f"Error: Timeout occurred while waiting for an element. {e}"
    except Exception as e:
        return f"An unexpected error occurred: {e}"

def main():
    if len(sys.argv) < 2:
        print("Error: Reference number is required as a command-line argument.")
        sys.exit(1)

    ref_number = sys.argv[1]  # Get the reference number from the command line
    result = open_check_bill_page(ref_number)
    driver.quit()
    print(result)

if __name__ == "__main__":
    main()
#
# from selenium import webdriver
# from selenium.webdriver.common.by import By
# from selenium.webdriver.edge.service import Service
# from selenium.webdriver.edge.options import Options
# from selenium.webdriver.support.ui import WebDriverWait
# from selenium.webdriver.support import expected_conditions as EC
# from selenium.common.exceptions import TimeoutException
# import sys
#
# # Initialize Edge WebDriver
# options = Options()
# options.use_chromium = True  # Ensure Chromium-based Edge is used
#
# # Path to msedgedriver.exe
# msedgedriver_path = 'D:/msedgedriver.exe'
# service = Service(executable_path=msedgedriver_path)
#
# driver = webdriver.Edge(service=service, options=options)
#
# def wait_for_element_and_send_keys(locator_type, locator_value, text):
#     element = WebDriverWait(driver, 10).until(
#         EC.presence_of_element_located((locator_type, locator_value))
#     )
#     element.send_keys(text)
#
# def open_check_bill_page(ref_number):
#     url = "http://www.lesco.gov.pk:36269/Modules/CustomerBillN/CheckBill.asp"
#     driver.get(url)
#
#     # Fill form fields
#     wait_for_element_and_send_keys(By.NAME, 'txtBatchNo', ref_number.split('-')[0])
#     wait_for_element_and_send_keys(By.NAME, 'txtSubDiv', ref_number.split('-')[1])
#     wait_for_element_and_send_keys(By.NAME, 'txtRefNo', ref_number.split('-')[2])
#
#     # Handle RU dropdown
#     dropdown = WebDriverWait(driver, 10).until(
#         EC.presence_of_element_located((By.NAME, 'cmbRU'))
#     )
#     if 'r' == ref_number[-1].lower():
#         dropdown.send_keys('R')
#     else:
#         dropdown.send_keys('U')
#
#     # Submit the form
#     button = WebDriverWait(driver, 10).until(
#         EC.element_to_be_clickable((By.NAME, 'btnViewMenu'))
#     )
#     button.click()
#
#     try:
#         # Navigate to the account status page
#         button = WebDriverWait(driver, 10).until(
#             EC.element_to_be_clickable(
#                 (By.XPATH, "//form[@class='inline' and @action='AccountStatus.aspx']//button[@name='submit_param']")
#             )
#         )
#         driver.execute_script("arguments[0].scrollIntoView({block: 'center'});", button)
#         driver.execute_script("arguments[0].click();", button)
#
#         # Extract required details
#         customer_name = driver.find_element(
#             By.XPATH, "//div[@class='col-sm']/h5[normalize-space(text())='Customer Name:']/following-sibling::strong"
#         ).text.strip()
#         amount_within_due_date = driver.find_element(
#             By.XPATH, "//div[@class='col-sm']/h5[normalize-space(text())='Amount Payable Within Due Date:']/following-sibling::strong"
#         ).text.strip()
#         amount_after_due_date = driver.find_element(
#             By.XPATH, "//div[@class='col-sm']/h5[normalize-space(text())='Amount Payable After Due Date:']/following-sibling::strong"
#         ).text.strip()
#         due_date = driver.find_element(
#             By.XPATH, "//div[@class='col-sm']/h5[normalize-space(text())='Due Date:']/following-sibling::strong"
#         ).text.strip()
#
#         # Combine extracted details
#         return (
#             f"Customer Name: {customer_name}\n"
#             f"Amount Payable Within Due Date: {amount_within_due_date}\n"
#             f"Amount Payable After Due Date: {amount_after_due_date}\n"
#             f"Due Date: {due_date}"
#         )
#
#     except TimeoutException as e:
#         return f"Error: Timeout occurred. {e}"
#     except Exception as e:
#         return f"An unexpected error occurred: {e}"
#
# def main():
#     if len(sys.argv) < 2:
#         print("Error: Reference number is required as a command-line argument.")
#         sys.exit(1)
#
#     ref_number = sys.argv[1]
#     result = open_check_bill_page(ref_number)
#     driver.quit()
#     print(result)
#
# if __name__ == "__main__":
#     main()
# # '''
