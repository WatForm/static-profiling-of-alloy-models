package com.alloyprofiling.analysiscomplexity;

import com.alloyprofiling.analysiscomplexity.multifile.partialtotalfuncs.OverallPartialFuncs;
import com.alloyprofiling.analysiscomplexity.multifile.partialtotalfuncs.OverallTotalFuncs;
import com.alloyprofiling.analysiscomplexity.multifile.partialtotalfuncs.UserTotalFuncs;
import com.alloyprofiling.analysiscomplexity.multifile.secondorderoperator.SetCardinality;
import com.alloyprofiling.analysiscomplexity.multifile.secondorderoperator.TCOperators;

import java.io.FileNotFoundException;

public class ProfilerComplexity {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("No argument given -- consult README file");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "setcardinality":
                SetCardinality.main(null);
                break;
            case "transitiveclosure":
                TCOperators.main(null);
                break;
            case "usertotalfuncs":
                UserTotalFuncs.main(null);
                break;
            case "overallpartial":
                OverallPartialFuncs.main(null);
                break;
            case "overalltotal":
                OverallTotalFuncs.main(null);
                break;
            default:
                System.out.println("Invalid argument -- consult README file");
        }
    }
}
