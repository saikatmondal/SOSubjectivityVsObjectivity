
library(effsize)

graphics.off()
par(mfrow=c(1,1))

codeReadCSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/Code_Readability_Score_GT_0.csv", header = T)
codeReadCSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/Code_Readability_Score_LT_0.csv", header = T)

codeReadJH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/Code_Readability_Score_GT_0.csv", header = T)
codeReadJL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/Code_Readability_Score_LT_0.csv", header = T)

codeReadJSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/Code_Readability_Score_GT_0.csv", header = T)
codeReadJSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/Code_Readability_Score_LT_0.csv", header = T)

codeReadPyH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/Code_Readability_Score_GT_0.csv", header = T)
codeReadPyL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/Code_Readability_Score_LT_0.csv", header = T)

codeReadH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_GT_0.csv", header = T)
codeReadL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_LT_0.csv", header = T)

codeReadRH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_RS_GT_0.csv", header = T)
codeReadRL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/CodeReadability/Code_Readability_Score_LT_0.csv", header = T)


#C-Sharp
crCSH = codeReadCSH$Score
crCSL = codeReadCSL$Score

summary(crCSH)
summary(crCSL)

wilcox.test(crCSH,crCSL)
cliff.delta(crCSH,crCSL)

#Java
crJH = codeReadJH$Score
crJL = codeReadJL$Score

summary(crJH)
summary(crJL)

wilcox.test(crJH,crJL)
cliff.delta(crJH,crJL)

#JavaScript
crJSH = codeReadJSH$Score
crJSL = codeReadJSL$Score

summary(crJSH)
summary(crJSL)

wilcox.test(crJSH,crJSL)
cliff.delta(crJSH,crJSL)

#Python
crPyH = codeReadPyH$Score
crPyL = codeReadPyL$Score

summary(crPyH)
summary(crPyL)

wilcox.test(crPyH,crPyL)
cliff.delta(crPyH,crPyL)

#Combile
crH = codeReadH$Score
crL = codeReadL$Score

summary(crH)
summary(crL)

wilcox.test(crH,crL)
cliff.delta(crH,crL)

#Random Sampled

crRH = codeReadRH$Score
crRL = codeReadRL$Score

summary(crRH)
summary(crRL)

wilcox.test(crRH,crRL)
cliff.delta(crRH,crRL)

#Ploting : Box Plot - Languages

boxplot(crCSH, crCSL, crJH, crJL,crJSH, crJSL, crPyH, crPyL,crH, crL, crRH, crRL,
        at=c(1,2, 4,5, 7,8, 10,11, 13,14, 16,17), xaxt="n", ylab="Code Readability", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5, 10.5, 13.5, 16.5),  labels= c("C#","Java","JavaScript","Python","All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)
abline(v=9, lty=2)
abline(v=12, lty=2)
abline(v=15, lty=2)

legend(15.6,.55,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")


#Ploting : Box Plot - Total & Random Sampled

boxplot(crH, crL, crRH, crRL,
        at=c(1,2, 4,5), xaxt="n", ylab="Title Quality (Recall)", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5),  labels= c("All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)

legend(4.5,0.3,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")






