
library(effsize)

graphics.off()
par(mfrow=c(1,1))

codeUnderCSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/APICalls_Score_GT_0.csv", header = T)
codeUnderCSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/C#/APICalls_Score_LT_0.csv", header = T)

codeUnderJH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/APICalls_Score_GT_0.csv", header = T)
codeUnderJL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Java/APICalls_Score_LT_0.csv", header = T)

codeUnderJSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/APICalls_Score_GT_0.csv", header = T)
codeUnderJSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/JavaScript/APICalls_Score_LT_0.csv", header = T)

codeUnderPyH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/APICalls_Score_GT_0.csv", header = T)
codeUnderPyL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/Python/APICalls_Score_LT_0.csv", header = T)

codeUnderH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_GT_0.csv", header = T)
codeUnderL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_LT_0.csv", header = T)

codeUnderRH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_RS_GT_0.csv", header = T)
codeUnderRL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-B(TextCode)/CombinedLanguages/Understandability/APICalls_Score_LT_0.csv", header = T)


#C-Sharp
cuCSH = codeUnderCSH$NoOfAPICalls
cuCSL = codeUnderCSL$NoOfAPICalls

summary(cuCSH)
summary(cuCSL)

wilcox.test(cuCSH,cuCSL)
cliff.delta(cuCSH,cuCSL)

#Java
cuJH = codeUnderJH$NoOfAPICalls
cuJL = codeUnderJL$NoOfAPICalls

summary(cuJH)
summary(cuJL)

wilcox.test(cuJH,cuJL)
cliff.delta(cuJH,cuJL)

#JavaScuipt
cuJSH = codeUnderJSH$NoOfAPICalls
cuJSL = codeUnderJSL$NoOfAPICalls

summary(cuJSH)
summary(cuJSL)

wilcox.test(cuJSH,cuJSL)
cliff.delta(cuJSH,cuJSL)

#Python
cuPyH = codeUnderPyH$NoOfAPICalls
cuPyL = codeUnderPyL$NoOfAPICalls

summary(cuPyH)
summary(cuPyL)

wilcox.test(cuPyH,cuPyL)
cliff.delta(cuPyH,cuPyL)

#Combile
cuH = codeUnderH$APICall
cuL = codeUnderL$APICall

summary(cuH)
summary(cuL)

wilcox.test(cuH,cuL)
cliff.delta(cuH,cuL)

#Random Sampled

cuRH = codeUnderRH$Score
cuRL = codeUnderRL$APICall

summary(cuRH)
summary(cuRL)

wilcox.test(cuRH,cuRL)
cliff.delta(cuRH,cuRL)

#Ploting : Box Plot - Languages

boxplot(cuCSH, cuCSL, cuJH, cuJL,cuJSH, cuJSL, cuPyH, cuPyL,cuH, cuL, cuRH, cuRL,
        at=c(1,2, 4,5, 7,8, 10,11, 13,14, 16,17), xaxt="n", ylab="# of API usage", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5, 10.5, 13.5, 16.5),  labels= c("C#","Java","JavaScript","Python","All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)
abline(v=9, lty=2)
abline(v=12, lty=2)
abline(v=15, lty=2)

legend(15.6,30,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")


#Ploting : Box Plot - Total & Random Sampled

boxplot(crH, crL, crRH, crRL,
        at=c(1,2, 4,5), xaxt="n", ylab="Title Quality (Recall)", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray"))
axis(side=1,at=c(1.5,2),  labels= c("All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)

legend(4.5,0.3,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")






