package User_Online;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BillLoader {

    private String pythonScriptPath;

    public BillLoader(String pythonScriptPath) {
        this.pythonScriptPath = pythonScriptPath;
    }

    public String[] fetchBillDetails(String refNumber) throws Exception {
        String[] command = {"python", pythonScriptPath, refNumber};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Python script execution failed. Exit code: " + exitCode);
        }

        String[] result = output.toString().trim().split("\\|");
        if (result.length != 4) {
            throw new RuntimeException("Invalid output from Python script: " + output);
        }

        return result; // [Customer Name, Amount Within Due Date, Amount After Due Date, Due Date]
    }
}
