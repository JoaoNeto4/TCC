
package reconhecimento;

//import org.bytedeco.javacpp.opencv_face.FaceRecognizer;

import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;



/*
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
*/


public class TesteJavaCV {
    
    public static void main(String[] args) {
        //em executor: java -Djava.library.path="/home/melena/NetBeansProjects/RF-teste/jar_files(1)"
        
        //System.loadLibrary (Core.NATIVE_LIBRARY_NAME);
        //System.setProperty("java.library.path", "/home/melena/NetBeansProjects/RF-teste/jar_files(1)");
        //System.out.println(System.getProperty("Java.library.path"));
        
        FaceRecognizer r = EigenFaceRecognizer.create();
        LBPHFaceRecognizer j = LBPHFaceRecognizer.create();
    }
}
