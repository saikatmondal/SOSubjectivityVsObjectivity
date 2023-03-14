
library(effsize)

graphics.off()
par(mfrow=c(2,2))

txtReadCSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/TextReadability/Text_Readability_Score_GT_0.csv", header = T)
txtReadCSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/C#/TextReadability/Text_Readability_Score_LT_0.csv", header = T)

txtReadJH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/TextReadability/Text_Readability_Score_GT_0.csv", header = T)
txtReadJL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Java/TextReadability/Text_Readability_Score_LT_0.csv", header = T)

txtReadJSH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/TextReadability/Text_Readability_Score_GT_0.csv", header = T)
txtReadJSL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/JavaScript/TextReadability/Text_Readability_Score_LT_0.csv", header = T)

txtReadPyH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TextReadability/Text_Readability_Score_GT_0.csv", header = T)
txtReadPyL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TextReadability/Text_Readability_Score_LT_0.csv", header = T)

txtReadH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/CombineResults/TextReadability/TextReadability_Score_GT_0.csv", header = T)
txtReadL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/CombineResults/TextReadability/TextReadability_Score_LT_0.csv", header = T)


#C-Sharp
readCSH = txtReadCSH$Score
readCSL = txtReadCSL$Score

summary(readCSH)
summary(readCSL)

wilcox.test(readCSH,readCSL)
cliff.delta(readCSH,readCSL)

#Java
readJH = txtReadJH$Score
readJL = txtReadJL$Score

summary(readJH)
summary(readJL)

#JavaScript
readJSH = txtReadJSH$Score
readJSL = txtReadJSL$Score

summary(readJSH)
summary(readJSL)

#Python
readPyH = txtReadPyH$Score
readPyL = txtReadPyL$Score

summary(readPyH)
summary(readPyL)

#Combile
readH = txtReadH$Score
readL = txtReadL$Score

summary(readH)
summary(readL)

#Random Sampled


#Plot : Boxplot

boxplot(readCSH, readCSL, readJH, readJL,readJSH,readJSL,readPyH,readPyL,readH,readL,
        at=c(1,2, 4,5, 7,8, 10,11, 13,14), xaxt="n", ylab="Readability", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5,10.5,13.5),  labels= c("C#","Java","JavaScript","Python","All"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)
abline(v=9, lty=2)
abline(v=12, lty=2)

legend(1,25,legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1.5, pt.cex = 2.5, pch=c(0,15), box.col = "white", bg = "white")


wilcox.test(readH,readL)
cliff.delta(readH,readL)

wilcox.test(A,A1)
cliff.delta(A,A1)

wilcox.test(B,B1)
cliff.delta(B,B1)

wilcox.test(C,C1)
cliff.delta(C,C1)

wilcox.test(D,D1)
cliff.delta(D,D1)


topicH = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TagEntropy/TagEntropy_Text_GT_0_v5.csv", header = T)
topicL = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/TextOnly/Python/TagEntropy/TagEntropy_Text_LT_0_v5.csv", header = T)

tH = topicH$TagEntropy
tL = topicL$TagEntropy

#tH = ((tH - min(tH))/(max(tH)-min(tH)))*100
#tL = ((tL - min(tL))/(max(tL)-min(tL)))*100


plot(density(tH), type="l", pch=16, ylab="Density", xlab="Topic Entropy", 
     lwd=1, lty=1, col="green", main = "")
lines(density(tL), type="l", pch=16, xaxt="n", yaxt="n", xlab="",lwd=1, lty=1,
      col="red")


boxplot(tH, tL,
        at=c(1,2), xaxt="n", ylab="Entropy", outline = F,cex.lab=1.3,
        col = c("white","gray"))
#axis(side=1,at=c(1.5,4.5,7.5,10.5,13.5),  labels= c("High","Low"), cex.axis = 1.3)




#Making Color
mycol = rgb(0, 0, 50, max = 255, alpha = 130, names = "blue50")

#High Total vs Low

plot (density(nreadHighT), type="l", pch=16, lwd=2, lty=1,ylab="Density", xlab="Readability", col="gray", cex.lab=1.2, main = "")

polygon(density(nreadHighT), col="gray", border="gray")

lines (density(nreadLow), type="l", pch=16 ,lwd=2, lty=1, ylab="Density", xlab="Readability",
       col=mycol)

polygon(density(nreadLow), col=mycol, border="black")

legend("topright", legend=c("Promoted", "Discouraged"),
       fill=c("gray", mycol), lty=1, lwd=2, cex=1.05)




#High Random Sample vs Low
plot (density(nreadHigh), type="l", pch=16, lwd=2, lty=1,ylab="Density", xlab="Readability", col="gray",cex.lab=1.2, main = "")

polygon(density(nreadHigh), col="gray", border="gray")

lines (density(nreadLow), type="l", pch=16 ,lwd=2, lty=1, ylab="Density", xlab="Readability",
       col=mycol)

polygon(density(nreadLow), col=mycol, border="black")

legend("topright", legend=c("Promoted", "Discouraged"),
       fill=c("gray", mycol), lty=1, lwd=2, cex=1.05)


















boxplot(nreadHighT, nreadLow,
        border=c("green","red"), 
        ylab="Readability", 
        outline = T,
        lwd=2, at=c(1,3),
        names=c("Promoted", "Discouraged")
)

boxplot(nreadHighT, nreadLow,
        border=c("green","red"), 
        ylab="Readability", 
        outline = F,
        lwd=2, at=c(1,2),
        names=c("Promoted", "Discouraged")
)


boxplot(nreadHigh, nreadLow,
        border=c("green","red"), 
        ylab="Readability", 
        outline = T,
        lwd=2, at=c(1,2),
        names=c("Promoted", "Discouraged")
)

boxplot(nreadHigh, nreadLow,
        border=c("green","red"), 
        ylab="Readability", 
        outline = F,
        lwd=2, at=c(1,2),
        names=c("Promoted", "Discouraged")
)



boxplot(A,A1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2),names=c("Promoted", "Discouraged"))

boxplot(B,B1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2), names=c("Promoted", "Discouraged"))

boxplot(C,C1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2), names=c("Promoted", "Discouraged"))

boxplot(D,D1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2),names=c("Promoted", "Discouraged"))


wilcox.test(nreadHighT,nreadLow)
kruskal.test(nreadLow,nreadHigh)
mood.test(nreadHigh,nreadLow)

summary(nreadHighT)
summary(nreadLow)
cohen.d(nreadHighT,nreadLow)

cliff.delta(nreadHighT,nreadLow)
ks.test(nreadHighT,nreadLow)

t.test(nreadHighT,nreadLow)
cohen.d(nreadHighT,nreadLow)

cor.test(nreadHigh,nreadLow)


wilcox.test(A,A1)
cliff.delta(A,A1)

wilcox.test(B,B1)
cliff.delta(B,B1)

wilcox.test(C,C1)
cliff.delta(C,C1)

wilcox.test(D,D1)
cliff.delta(D,D1)


