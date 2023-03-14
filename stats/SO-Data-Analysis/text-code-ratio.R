
library(effsize)

graphics.off()
par(mfrow=c(1,1))

tcrCSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/TCR_Score_GT_0.csv", header = T)
tcrCSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/TCR_Score_LT_0.csv", header = T)

tcrJH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/TCR_Score_GT_0.csv", header = T)
tcrJL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/TCR_Score_LT_0.csv", header = T)

tcrJSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/TCR_Score_GT_0.csv", header = T)
tcrJSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/TCR_Score_LT_0.csv", header = T)

tcrPyH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/TCR_Score_GT_0.csv", header = T)
tcrPyL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/TCR_Score_LT_0.csv", header = T)

tcrH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_GT_0.csv", header = T)
tcrL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_LT_0.csv", header = T)

tcrRH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_RS_GT_0.csv", header = T)
tcrRL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/TCR_Score_LT_0.csv", header = T)


#C-Sharp
crCSH = tcrCSH$Ratio
crCSL = tcrCSL$Ratio

summary(crCSH)
summary(crCSL)

wilcox.test(crCSH,crCSL)
cliff.delta(crCSH,crCSL)

#Java
crJH = tcrJH$Ratio
crJL = tcrJL$Ratio

summary(crJH)
summary(crJL)

wilcox.test(crJH,crJL)
cliff.delta(crJH,crJL)

#JavaScript
crJSH = tcrJSH$Ratio
crJSL = tcrJSL$Ratio

summary(crJSH)
summary(crJSL)

wilcox.test(crJSH,crJSL)
cliff.delta(crJSH,crJSL)

#Python
crPyH = tcrPyH$Ratio
crPyL = tcrPyL$Ratio

summary(crPyH)
summary(crPyL)

wilcox.test(crPyH,crPyL)
cliff.delta(crPyH,crPyL)

#Combile
crH = tcrH$TextCodeRatio
crL = tcrL$TextCodeRatio

summary(crH)
summary(crL)

wilcox.test(crH,crL)
cliff.delta(crH,crL)

#Random Sampled

crRH = tcrRH$Score
crRL = tcrRL$TextCodeRatio

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

legend(16,12,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")



#Density Plot

plot(density(crRH), type="l", pch=16, ylab="Density", xlab="Readability", 
     lwd=1, lty=1, col="green", main = "")
lines(density(crRL), type="l", pch=16, xaxt="n", yaxt="n", xlab="",lwd=1, lty=1,
      col="red")



#Ploting : Box Plot - Total & Random Sampled

boxplot(crH, crL, crRH, crRL,
        at=c(1,2, 4,5), xaxt="n", ylab="Title Quality (Recall)", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5),  labels= c("All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)

legend(4.5,0.3,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")



CS = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/TextCodeRatio/Chi_Square_data.csv", header = T)

# Chi-sq test
chisq.test(CS$prmoted, CS$discourgaed, correct=FALSE)

