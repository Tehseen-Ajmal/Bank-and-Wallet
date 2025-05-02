package myon.bank.pack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class EmailSender extends Email {
    private SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    private SimpleDateFormat stf = new SimpleDateFormat("hh:mm:ss a");

    private String otp() {
        int r;
        String text = "0123456789";
        String tex = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            r = random.nextInt(9);
            tex += text.charAt(r);
        }
        return tex;
    }

    public String acc_hash(String num) {
        String x_num = "";
        x_num = num.substring(0, 4) + "******" + num.substring(10);
        return x_num;
    }

    public boolean otp_send(String to, String user) {
        char[] tex = otp().toCharArray();
        String t = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>First-Time Registration OTP</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: 'Arial', sans-serif;\n" +
                "      background-color: #f0f0f0;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    .email-container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 20px auto;\n" +
                "      padding: 20px;\n" +
                "      background-color: #ecf0f5;\n" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "      border-radius: 10px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #3498db;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "    }\n" +
                "    .otp-container {\n" +
                "      background-color: #3498db;\n" +
                "      color: #fff;\n" +
                "      padding: 15px;\n" +
                "      text-align: center;\n" +
                "      font-size: 24px;\n" +
                "      margin-top: 20px;\n" +
                "      border-radius: 8px;\n" +
                "      white-space: nowrap; /* Prevents line breaks */\n" +
                "    }\n" +
                "    .otp-digit {\n" +
                "      display: inline-block;\n" +
                "      background-color: #dcdcdc;\n" +
                "      color: #111122; /*#333;*/\n" +
                "      font-size: 18px;\n" +
                "      width: 30px;\n" +
                "      height: 30px;\n" +
                "      text-align: center;\n" +
                "      line-height: 30px;\n" +
                "      margin: 0 5px;\n" +
                "      border-radius: 5px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <h1>Welcome to %s!</h1>\n" +
                "\n" +
                "    <p>Dear %s,</p>\n" +
                "\n" +
                "    <p>Congratulations on successfully registering with us! To complete your registration, please use the following One-Time Password (OTP):</p>\n" +
                "\n" +
                "    <div class=\"otp-container\">\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "      <span class=\"otp-digit\">%s</span>\n" +
                "    </div>\n" +
                "\n" +
                "    <p>This OTP is valid for a single use and ensures the security of your account. Please do not share it with anyone.</p>\n" +
                "\n" +
                "    <p>If you have any questions or need assistance, feel free to contact our support team.</p>\n" +
                "\n" +
                "    <p>Thank you for choosing our Bank!</p>\n" +
                "\n" +
                "    <p>Best regards,<br>%s</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n", bank, user, tex[0], tex[1], tex[2], tex[3], tex[4], tex[5], bank);
        boolean stat = send_mail(to, "Email Verification", t, user);
        if (stat) {
            System.out.println("message Sent");
        } else {
            System.out.println("failed");
        }
        return stat;
    }

    public boolean deposit_receipt(String to, String user, String acc_num1, float total, float deposit) {
        String subject = "Deposit Receipt";
        String acc_num = acc_hash(acc_num1);
        Date d = new Date();
        String date = sdf.format(d);
        String time = stf.format(d);
        String dep_rec = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Deposit Receipt</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: 'Arial', sans-serif;\n" +
                "      background-color: #f4f4f4;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    .receipt-container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 20px auto;\n" +
                "      padding: 20px;\n" +
                "      background-color: #fff;\n" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "      border-radius: 10px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #3498db;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "    }\n" +
                "    .transaction-details {\n" +
                "      margin-top: 20px;\n" +
                "    }\n" +
                "    .deposit, .total-remaining {\n" +
                "      margin-top: 15px;\n" +
                "      padding: 10px;\n" +
                "      border-radius: 5px;\n" +
                "    }\n" +
                "    .deposit {\n" +
                "      background-color: #99ff99;\n" +
                "    }\n" +
                "    .total-remaining {\n" +
                "      background-color: #3498db;\n" +
                "      color: #fff;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"receipt-container\">\n" +
                "    <h1>Deposit Receipt</h1>\n" +
                "\n" +
                "    <p>Customer Name: %s</p>\n" +
                "    <p>Account Number: %s</p>\n" +
                "\n" +
                "    <div class=\"transaction-details\">\n" +
                "      <div class=\"deposit\">\n" +
                "        <p><strong>Deposit</strong></p>\n" +
                "        <p>Amount: $%.2f</p>\n" +
                "        <p>Date: %s</p>\n" +
                "        <p>Time: %s</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"total-remaining\">\n" +
                "      <p><strong>Total Amount</strong></p>\n" +
                "      <p>$%.2f</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <p>If you have any questions or concerns, please contact our customer support.</p>\n" +
                "\n" +
                "    <p>Thank you for banking with us!</p>\n" +
                "    <p>Best regards,<br>%s</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n", user, acc_num, deposit, date, time, total, bank);
        boolean stat = send_mail(to, subject, dep_rec, user);
        if (stat) {
            System.out.println("message Sent");
        } else {
            System.out.println("failed");
        }
        return stat;
    }

    public boolean withdraw_receipt(String to, String user, String acc_num1, float remain, float withdraw) {
        String subject = "Withdrawal Receipt";
        String acc_num = acc_hash(acc_num1);
        Date d = new Date();
        String date = sdf.format(d);
        String time = stf.format(d);

        String with_rec = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Withdrawal Receipt</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: 'Arial', sans-serif;\n" +
                "      background-color: #f4f4f4;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    .receipt-container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 20px auto;\n" +
                "      padding: 20px;\n" +
                "      background-color: #fff;\n" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "      border-radius: 10px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #3498db;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "    }\n" +
                "    .transaction-details {\n" +
                "      margin-top: 20px;\n" +
                "    }\n" +
                "    .withdrawal, .total-remaining {\n" +
                "      margin-top: 15px;\n" +
                "      padding: 10px;\n" +
                "      border-radius: 5px;\n" +
                "    }\n" +
                "    .withdrawal {\n" +
                "      background-color: #ff9999;\n" +
                "    }\n" +
                "    .total-remaining {\n" +
                "      background-color: #3498db;\n" +
                "      color: #fff;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"receipt-container\">\n" +
                "    <h1>Withdrawal Receipt</h1>\n" +
                "\n" +
                "    <p>Customer Name: %s</p>\n" +
                "    <p>Account Number: %s</p>\n" +
                "\n" +
                "    <div class=\"transaction-details\">\n" +
                "      <div class=\"withdrawal\">\n" +
                "        <p><strong>Withdrawal</strong></p>\n" +
                "        <p>Amount: $%.2f</p>\n" +
                "        <p>Date: %s</p>\n" +
                "        <p>Time: %s</p>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"total-remaining\">\n" +
                "      <p><strong>Total Amount</strong></p>\n" +
                "      <p>$%.2f</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <p>If you have any questions or concerns, please contact our customer support.</p>\n" +
                "\n" +
                "    <p>Thank you for banking with us!</p>\n" +
                "    <p>Best regards,<br>%s</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n", user, acc_num, withdraw, date, time, remain, bank);
        boolean stat = send_mail(to, subject, with_rec, user);
        if (stat) {
            System.out.println("message Sent");
        } else {
            System.out.println("failed");
        }
        return stat;
    }

    public boolean reset_pass(String to, String user, String account_num) {
        String acc_num = acc_hash(account_num);
        String subject = "Password Reset";
        String otp = otp().toString();
        String text = String.format("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Forgot Password OTP</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: 'Arial', sans-serif;\n" +
                "      background-color: #f0f0f0;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    .email-container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 20px auto;\n" +
                "      padding: 20px;\n" +
                "      background-color: #ecf0f5;\n" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "      border-radius: 10px;\n" +
                "    }\n" +
                "    h1 {\n" +
                "      color: #3498db;\n" +
                "    }\n" +
                "    p {\n" +
                "      color: #555;\n" +
                "    }\n" +
                "    .otp-container {\n" +
                "      background-color: #52A6DF;\n" +
                "      color: #fff;\n" +
                "      padding: 10px;\n" +
                "      text-align: center;\n" +
                "      font-size: 24px;\n" +
                "      margin-top: 20px;\n" +
                "      border-radius: 8px;\n" +
                "    }\n" +
                "    .otp-text {\n" +
                "      font-size: 32px;\n" +
                "      font-weight: bold;\n" +
                "    }\n" +
                "    .instructions {\n" +
                "      margin-top: 20px;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <h1>Forgot Password OTP</h1>\n" +
                "\n" +
                "    <p>Dear %s,</p>\n" +
                "\n" +
                "    <p>We received a request to reset your password. To continue, please use the following One-Time Password (OTP):</p>\n" +
                "\n" +
                "    <div class=\"otp-container\">\n" +
                "      <p class=\"otp-text\">%s</p>\n" +
                "    </div>\n" +
                "\n" +
                "<div class=\"accnum\" >" +
                "      <p class=\"acc\">Account Number: %s</p>\n" +
                "</div>\n" +
                "    <div class=\"instructions\">\n" +
                "      <p>This OTP is valid for a single use and ensures the security of your account. Please do not share it with anyone.</p>\n" +
                "\n" +
                "      <p>If you did not request a password reset, please disregard this email.</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <p>If you have any questions or need assistance, feel free to contact our support team.</p>\n" +
                "\n" +
                "    <p>Thank you for choosing %s!</p>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>\n", user, otp, acc_num, bank);
        boolean stat = send_mail(to, subject, text, user);
        if (stat) {
            System.out.println("message Sent");
        } else {
            System.out.println("failed");
        }
        return stat;

    }
}
