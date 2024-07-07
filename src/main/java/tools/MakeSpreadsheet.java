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
        estiloCor.setFillForegroundColor(HSSFColor.WHITE.index);
        estiloCor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        estiloCor.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return estiloCor;
    }


    /**
        Aqui vai uma informação importante: o código já funcionava, anteriomente
        o problema é que o excel não tentende a formatação, (ainda não sei o motivo - talvez versão)
        quando a planilha foi subida para o googleSheets a visualização foi possível.
     **/
    public void buildMap(List<Bloco> historicoTotal, List<Bloco> blocosBloquiados){

        HSSFWorkbook workb = new HSSFWorkbook();
        HSSFSheet sheet = workb.createSheet("2024 - refactoring - save old code");

        for (int i = 1; i <= 15; i++) {
            HSSFRow row = sheet.createRow(i);

            for (int j = 1; j <= 15; j++) {

                String coordenada = i +"."+ j;

                System.out.println("========================================");
                System.out.println("preenchendo o campo: " + coordenada);

                HSSFCellStyle hssfCellStyle = corDefault(workb);
                HSSFCell cel = row.createCell(j);
                cel.setCellValue(new HSSFRichTextString(coordenada));

                if(historicoTotal.contains(new Bloco(i, j))){
                    System.out.println("produzindo bloco verde");
                    hssfCellStyle = corVerde(workb);

                }else if(blocosBloquiados.contains(new Bloco(i, j))){

                    System.out.println("produzindo bloco cinza");
                    hssfCellStyle = corCinza(workb);
                }

                System.out.println("a coodenada: " + coordenada + " tem a cor: "+ hssfCellStyle.getFillForegroundColor());
                cel.setCellStyle(hssfCellStyle);
                System.out.println("========================================");
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

//        List<Bloco> historicoTotalMock = new ArrayList<Bloco>();
//        historicoTotalMock.add(new Bloco(1,1, true));
        robo.ande();
        makeSpreadsheet.buildMap(robo.getHistoricoTotal(), robo.getBlocosBloqueados());

    }


}
