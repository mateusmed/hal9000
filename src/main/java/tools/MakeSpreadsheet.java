package tools;

import entity.Bloco;
import entity.Robo;
import main.Main;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;


import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MakeSpreadsheet {

    private final String DIR = "C:\\dev\\workbook.xls";

    public HSSFCellStyle corCinza(HSSFWorkbook workb){

        HSSFCellStyle estiloCor = workb.createCellStyle();
        estiloCor.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        estiloCor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        return estiloCor;
    }

    public HSSFCellStyle corVerde(HSSFWorkbook workb){

        HSSFCellStyle estiloCor = workb.createCellStyle();
        estiloCor.setFillForegroundColor(HSSFColor.GREEN.index);
        estiloCor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return estiloCor;
    }

    public HSSFCellStyle corDefault(HSSFWorkbook workb){

        HSSFCellStyle estiloCor = workb.createCellStyle();
        estiloCor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        return estiloCor;
    }

    public void buildMap(List<Bloco> historicoTotal, List<Bloco> blocosBloquiados){

        HSSFWorkbook workb = new HSSFWorkbook();
        HSSFSheet sheet = workb.createSheet("Pedro e Mateus é nota total");


        for (int i = 1; i <= 15; i++) {
            HSSFRow row = sheet.createRow(i);

            for (int j = 1; j <= 15; j++) {

                sheet.setColumnWidth(j, 1500);
                HSSFCellStyle hssfCellStyle = corDefault(workb);

                if(historicoTotal.contains(new Bloco(i, j, true))){

                    hssfCellStyle = corVerde(workb);
                }else if(blocosBloquiados.contains(new Bloco(i, j, false))){
                    hssfCellStyle = corCinza(workb);
                }

                HSSFCell cel = row.createCell(j);

                cel.setCellStyle(hssfCellStyle);
                cel.setCellValue(new HSSFRichTextString(i +"."+ j));

                sheet = workb.getSheetAt(0);
                sheet.autoSizeColumn((short) 0);
            }
        }


        try {
            FileOutputStream fileOut = new FileOutputStream(DIR);
            workb.write(fileOut);
            fileOut.close();

            Desktop.getDesktop().open(new File(DIR));
        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {

        MakeSpreadsheet makeSpreadsheet = new MakeSpreadsheet();

        Main m = new Main();

        Robo robo = new Robo();

        m.mapaCaio(robo);

        System.out.println(robo.getBlocosBloqueados());

        List<Bloco> historicoTotalMock = new ArrayList<Bloco>();

        historicoTotalMock.add(new Bloco(1,1, true));

        makeSpreadsheet.buildMap(historicoTotalMock, robo.getBlocosBloqueados());

    }


}
