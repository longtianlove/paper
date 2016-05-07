import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by 58 on 2016/5/6.
 */
public class transform {
    public static void main(String[] args){
        File fileCode=new File("C:\\Users\\58\\Desktop\\code.txt");
        File fileResult=new File("C:\\Users\\58\\Desktop\\codeResult.txt");
        try {
            if(!fileCode.exists()){
                fileCode.createNewFile();
            }
            if(!fileResult.exists()){
                fileResult.createNewFile();
            }
            FileOutputStream outputStream=new FileOutputStream(fileResult);
           Writer writer=new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileCode)));
            String result="";
            while (!"".equals(result=reader.readLine())){
                String a=result.substring(5);
                if(!"".equals(a)) {
                    outputStream.write(a.getBytes());
//                outputStream.write("/n".getBytes());
                    System.out.println(a);
//                writer.newLine();
                }
            }

//            writer.flush();
            outputStream.close();
            reader.close();
        }catch (Exception e){

        }

    }

}
