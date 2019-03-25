import java.util.ArrayList;
import java.util.Arrays;

public class GradientDescent{

    private static ArrayList<Integer> xS;
    private static ArrayList<Integer> yS;
    private static ArrayList<Integer> testDataXs;
    private static ArrayList<Integer> testDataYs;
    private static ArrayList<Float> testDataYsResults= new ArrayList<>();
    private static float learning_rate  = 0.0001f;
    private static int iterations = 10000000 ;
    private static float resultA;
    private static float resultB;

    public static void main(String[] args){
        loadInitialDatas();
        runGradientDescent();
        calculateResults();
        printResults();
        calculateMSE();
    }

    private static void calculateMSE() {
        float totalError = 0f;
        for (int i=0;i<testDataYs.size();i++){
            float error = testDataYs.get(i)-testDataYsResults.get(i);
            error *= error ;
            System.out.println("Error"+(i+1)+": "+error);
            totalError+=error;
        }
        System.out.println("Total MSE Error: "+totalError/testDataYsResults.size());
    }

    private static void calculateResults() {
        for (int i=0;i<testDataXs.size();i++){
            testDataYsResults.add(testDataXs.get(i)*resultA+resultB);
        }
    }

    private static void printResults() {
        for (int i=0;i<testDataYsResults.size();i++)
            System.out.println("At x:"+testDataXs.get(i)+"   ->   "+"  Initial Value: "+testDataYs.get(i)+"  , Estimated Value: "+testDataYsResults.get(i));
    }

    private static void runGradientDescent() {
        // y = ax + b line
        float aInitial=0f;
        float bInitial=0f;
        for (int i=0;i<iterations;i++) {

            float aGrad=0f;
            float bGrad=0f;

            for (int j=0;j<xS.size();j++){
                aGrad += (-2.0f/xS.size()) * (xS.get(j)) * (yS.get(j)- ((aInitial*xS.get(j))+bInitial));
                bGrad += (-2.0f/xS.size()) * (yS.get(j) - ((aInitial*xS.get(j))+ bInitial));
            }
            aInitial = aInitial - (learning_rate*aGrad);
            bInitial = bInitial - (learning_rate*bGrad);
        }

        resultA = aInitial;
        resultB = bInitial;
    }


    private static void loadInitialDatas(){
        xS = new ArrayList<>(Arrays.asList(39,47,45,47,65,46,67,42,67,56,64,56,59,34,42,48,45,17,20,19,36,50,39,21,44));
        yS = new ArrayList<>(Arrays.asList(144,220,138,145,162,142,170,124,158,154,162,150,140,110,128,130,135,114,116,124,136,142,120,120,160));
        testDataXs = new ArrayList<>(Arrays.asList(53,63,29,25,69));
        testDataYs = new ArrayList<>(Arrays.asList(158,144,130,125,175));
    }

}

