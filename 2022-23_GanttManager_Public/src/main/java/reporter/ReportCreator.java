package reporter;

import parser.FileHandler;
import backend.MainController;
import dom2app.SimpleTableModel;

import java.util.List;
import java.io.IOException;

public class ReportCreator {
    private String exportType;
    private String filename;
    private FileHandler fileHandler;
    private static MainController myDatas;
    private SimpleTableModel s;
    private List<String[]> myDatasList;

    public ReportCreator(MainController mainController) {
        this.myDatas = mainController;
    }

    private int writeReport(String[] data) {
        fileHandler = new FileHandler(filename);
        
        try {
            fileHandler.createWriterFD();
            String content = String.join("", data);
            fileHandler.writeToFile(content);
            return 0; // Successful write
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error occurred
        } finally {
            // Ensure that file handler is closed regardless of success or failure
            fileHandler.closeFD();
        }
    }


    public int writeAsHtml(String filename, String delimiter) {
        fileHandler = new FileHandler(filename);
        try {
            fileHandler.createWriterFD();
            myDatas.load(myDatas.getFile(), delimiter);
            List<String[]> dataList = myDatas.getS().getData();
            StringBuilder constructedData = new StringBuilder();
            constructedData.append("<!doctype html>\n")
                    .append("<html>\n<head>\n")
                    .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1253\">\n")
                    .append("<title>Gantt Project Data</title>\n")
                    .append("</head>\n<body>\n<table>\n");

            for (String[] row : dataList) {
                constructedData.append("<tr>");
                for (String cell : row) {
                    constructedData.append("<td>").append(cell).append("</td>");
                }
                constructedData.append("</tr>\n");
            }
            constructedData.append("</table></body>\n</html>");

            fileHandler.writeToFile(constructedData.toString());
            return 0; // Successful write
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error occurred
        } finally {
            fileHandler.closeFD();
        }
    }

    public int writeAsMd(String delimiter) {
        fileHandler = new FileHandler(filename);
        try {
            fileHandler.createWriterFD();
            StringBuilder constructedData = new StringBuilder();
            constructedData.append("# Markdown Report\n\n");
            for (String[] row : myDatasList) {
                for (String cell : row) {
                    constructedData.append("*").append(cell).append("* ");
                }
                constructedData.append("\n");
            }
            
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            fileHandler.closeFD();
        }
    }

    public int writeAsTxt(String delimiter) {
        fileHandler = new FileHandler(filename);
        try {
            fileHandler.createWriterFD();
            StringBuilder constructedData = new StringBuilder();
            for (String[] row : myDatasList) {
                for (String cell : row) {
                    constructedData.append(cell).append("\t");
                }
                constructedData.append("\n");
            }
           
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            fileHandler.closeFD();
        }
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
