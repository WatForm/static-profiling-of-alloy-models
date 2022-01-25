getPercentDistr <- function(fileList) {
  total <- 0
  for (f in fileList) {
    values <- scan(f)
    sum_f <- sum(values)
    total <- total + sum_f
  }
  
  for (f in fileList) {
    values <- scan(f)
    sum_p <- sum(values)
    percentage <- (sum_p/total) * 100
    print(sprintf(paste("Percentage of", strsplit(f, "\\.")[[1]][[1]],"%.1f%%"), percentage))
  }
}

getPercentDistr_total <- function(fileList, total) {
  for (f in fileList) {
    values <- scan(f)
    sum_p <- sum(values)
    percentage <- (sum_p/total) * 100
    print(sprintf(paste("Percentage of", strsplit(f, "\\.")[[1]][[1]],"%.1f%%"), percentage))
  }
}

#getPercentDistr(c("Results\\PatternsOfUse\\MultiFile\\Modules\\userModules.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\boolean.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\integer.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\graph.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\naturals.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ordering.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\relation.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ternary.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\time.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seq.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seqrel.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\sequence.txt"))
#getPercentDistr(c("Results\\PatternsOfUse\\MultiFile\\Modules\\userModules_expert.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\boolean_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\integer_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\graph_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\naturals_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ordering_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\relation_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ternary_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\time_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seq_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seqrel_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\sequence_expert.txt"))
#getPercentDistr_total(c("Results\\PatternsOfUse\\MultiFile\\Modules\\userModules_mod.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\boolean_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\integer_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\integer_noMod.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\graph_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\naturals_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ordering_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\relation_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ternary_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\time_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seq_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seqrel_mod.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\sequence_mod.txt"), 1652)
#getPercentDistr_total(c("Results\\PatternsOfUse\\MultiFile\\Modules\\userModules_mod_expert.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\boolean_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\integer_noMod_expert.txt","Results\\PatternsOfUse\\MultiFile\\Modules\\graph_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\naturals_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ordering_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\relation_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\ternary_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\time_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seq_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\seqrel_mod_expert.txt", "Results\\PatternsOfUse\\MultiFile\\Modules\\sequence_mod_expert.txt"), 75)

#getPercentDistr(c("setExact.txt", "setNonExact.txt", "modelExact.txt", "defaultNonExact.txt","derivedExact.txt", "derivedNonExact.txt"))
#getPercentDistr(c("setExact_expert.txt", "setNonExact_expert.txt", "modelExact_expert.txt", "defaultNonExact_expert.txt","derivedExact_expert.txt", "derivedNonExact_expert.txt"))

#getPercentDistr(c("Results\\ModelingPractices\\intConst.txt", "Results\\ModelingPractices\\intDecls.txt"))

#getPercentDistr_total(c("Results\\ModelingPractices\\intNoMod.txt", "Results\\ModelingPractices\\intOpenMod.txt"),1049)
#getPercentDistr(c("Results\\CharacteristicsOfModels\\CornerCases\\none.txt", "Results\\CharacteristicsOfModels\\CornerCases\\univ.txt", "Results\\CharacteristicsOfModels\\CornerCases\\iden.txt"))

#getPercentDistr(c("Results\\PatternsOfUse\\MultiFile\\Formulas\\domPred.txt","Results\\PatternsOfUse\\MultiFile\\Formulas\\domRel.txt", "Results\\PatternsOfUse\\MultiFile\\Formulas\\domNav.txt", "Results\\PatternsOfUse\\MultiFile\\Formulas\\purePred.txt", "Results\\PatternsOfUse\\MultiFile\\Formulas\\pureRel.txt", "Results\\PatternsOfUse\\MultiFile\\Formulas\\pureNav.txt"))