package com.alloyprofiling.patternsofuse;


import com.alloyprofiling.patternsofuse.multifile.formulas.FormulaStyles;
import com.alloyprofiling.patternsofuse.multifile.integers.IntegerConstOrdering;
import com.alloyprofiling.patternsofuse.multifile.integers.IntegerOrdering;
import com.alloyprofiling.patternsofuse.multifile.integers.IntegerUse;
import com.alloyprofiling.patternsofuse.multifile.integers.SetCardEqualConst;
import com.alloyprofiling.patternsofuse.multifile.modules.IntegerLibrary;
import com.alloyprofiling.patternsofuse.multifile.modules.ModuleUse;
import com.alloyprofiling.patternsofuse.multifile.modules.ModuleUseModels;
import com.alloyprofiling.patternsofuse.multifile.scopes.IntegerScopes;
import com.alloyprofiling.patternsofuse.multifile.scopes.OrderingNonExact;
import com.alloyprofiling.patternsofuse.multifile.scopes.Scopes;
import com.alloyprofiling.patternsofuse.multifile.sets.SCG;
import com.alloyprofiling.patternsofuse.multifile.sets.SetHierarchyTrees;
import com.alloyprofiling.patternsofuse.multifile.sets.SigsAsStructures;

import java.io.FileNotFoundException;

public class ProfilerPatterns {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("No argument given -- consult README file");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "integerlibrary":
                IntegerLibrary.main(null);
                break;
            case "moduleuse":
                ModuleUse.main(null);
                break;
            case "moduleusemodels":
                ModuleUseModels.main(null);
                break;
            case "integeruse":
                IntegerUse.main(null);
                break;
            case "integerordering":
                IntegerOrdering.main(null);
                break;
            case "setcardequalconst":
                SetCardEqualConst.main(null);
                break;
            case "integerconstordering":
                IntegerConstOrdering.main(null);
                break;
            case "sethierarchygraphs":
                SetHierarchyTrees.main(null);
                break;
            case "scg":
                SCG.main(null);
                break;
            case "sigsasstructures":
                SigsAsStructures.main(null);
                break;
            case "formulasstyles":
                FormulaStyles.main(null);
                break;
            case "scopes":
                Scopes.main(null);
                break;
            case "orderingnonexact":
                OrderingNonExact.main(null);
                break;
            case "integerscopes":
                IntegerScopes.main(null);
                break;
            default:
                System.out.println("Invalid argument -- consult README file");
        }
    }
}
