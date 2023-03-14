
library(effsize)

graphics.off()
par(mfrow=c(1,1))

tQualityCSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/CS_Title_ROUGE_Score_GT_0.csv", header = T)
tQualityCSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/CS_Title_ROUGE_Score_LT_0.csv", header = T)

tQualityJH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Java_Title_ROUGE_Score_GT_0.csv", header = T)
tQualityJL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Java_Title_ROUGE_Score_LT_0.csv", header = T)

tQualityJSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/JS_Title_ROUGE_Score_GT_0.csv", header = T)
tQualityJSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/JS_Title_ROUGE_Score_LT_0.csv", header = T)

tQualityPyH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Py_Title_ROUGE_Score_GT_0.csv", header = T)
tQualityPyL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Py_Title_ROUGE_Score_LT_0.csv", header = T)

tQualityH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Title_Quality_Score_GT_0.csv", header = T)
tQualityL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Title_Quality_Score_LT_0.csv", header = T)

tQualityRH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Title_Quality_Score_RS_GT_0.csv", header = T)
tQualityRL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TitleQuality/Title_Quality_Score_RS_LT_0.csv", header = T)


#C-Sharp
tqCSH = tQualityCSH$Recall
tqCSL = tQualityCSL$Recall

summary(tqCSH)
summary(tqCSL)

wilcox.test(tqCSH,tqCSL)
cliff.delta(tqCSH,tqCSL)

#Java
tqJH = tQualityJH$Recall
tqJL = tQualityJL$Recall

summary(tqJH)
summary(tqJL)

wilcox.test(tqJH,tqJL)
cliff.delta(tqJH,tqJL)

#JavaScript
tqJSH = tQualityJSH$Recall
tqJSL = tQualityJSL$Recall

summary(tqJSH)
summary(tqJSL)

wilcox.test(tqJSH,tqJSL)
cliff.delta(tqJSH,tqJSL)

#Python
tqPyH = tQualityPyH$Recall
tqPyL = tQualityPyL$Recall

summary(tqPyH)
summary(tqPyL)

wilcox.test(tqPyH,tqPyL)
cliff.delta(tqPyH,tqPyL)

#Combile
tqH = tQualityH$Recall
tqL = tQualityL$Recall

summary(tqH)
summary(tqL)

wilcox.test(tqH,tqL)
cliff.delta(tqH,tqL)

#Random Sampled

tqRH = tQualityRH$Recall
tqRL = tQualityRL$Recall

summary(tqRH)
summary(tqRL)

wilcox.test(tqRH,tqRL)
cliff.delta(tqRH,tqRL)

#Ploting : Box Plot - Languages

boxplot(tqCSH, tqCSL, tqJH, tqJL,tqJSH, tqJSL, tqPyH, tqPyL,
        at=c(1,2, 4,5, 7,8, 10,11), xaxt="n", ylab="Title Quality (Recall)", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5, 10.5),  labels= c("C#","Java","JavaScript","Python"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)
abline(v=9, lty=2)

legend(9.2,0.3,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")

#Ploting : Box Plot - Total & Random Sampled

boxplot(tqH, tqL, tqRH, tqRL,
        at=c(1,2, 4,5), xaxt="n", ylab="Title Quality (Recall)", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5),  labels= c("All Samples","Random Sampled"), cex.axis = 1.3)

abline(v=3, lty=2)

legend(4.5,0.3,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1, pt.cex = 1.5, pch=c(0,15), box.col = "black", bg = "white")






