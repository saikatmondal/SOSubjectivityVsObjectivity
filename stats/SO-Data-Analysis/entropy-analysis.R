library(effsize)
graphics.off()
par(mfrow=c(1,1))

roughHighT=read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TagEntropy/Py_High.csv", header = T)
#summary(roughHighT)

#roughHigh = as.numeric(as.character(roughHighT$Recall))
#roughHigh[which(!is.finite(roughHigh))] = 0
#roughHigh = sample(roughHigh, 1000, replace = T)

roughHigh = roughHighT$TagEntropy
#roughHigh = roughHigh[0:374130]
summary(roughHigh)


roughLowT=read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/TagEntropy/Py_Low.csv", header = T)
#summary(roughLow)

#roughLow = as.numeric(as.character(roughLowT$Recall))
#roughLow[which(!is.finite(roughLow))] = 0
#roughLow = sample(roughLow, 1000, replace = F)

roughLow = roughLowT$TagEntropy
summary(roughLow)

A=roughHigh[(roughHigh>=0 & roughHigh < quantile(roughHigh,0.25))]
B=roughHigh[(roughHigh>=quantile(roughHigh,0.25) & roughHigh < quantile(roughHigh,0.50))]
C=roughHigh[(roughHigh>=quantile(roughHigh,0.50) & roughHigh < quantile(roughHigh,0.75))]
D=roughHigh[(roughHigh>=quantile(roughHigh,0.75) & roughHigh <= quantile( roughHigh,1.0))]


A1=roughLow[(roughLow>=0 & roughLow < quantile(roughLow,0.25))]
B1=roughLow[(roughLow>=quantile(roughLow,0.25) & roughLow < quantile(roughLow,0.50))]
C1=roughLow[(roughLow>=quantile(roughLow,0.50) & roughLow < quantile(roughLow,0.75))]
D1=roughLow[(roughLow>=quantile(roughLow,0.75) & roughLow <= quantile( roughLow,1.0))]



#wilcox.test(roughHigh,roughLow)
cliff.delta(roughHigh,roughLow)

#wilcox.test(A,A1)
cliff.delta(A,A1)

#wilcox.test(B,B1)
cliff.delta(B,B1)

#wilcox.test(C,C1)
cliff.delta(C,C1)


cliff.delta(D,D1)
#wilcox.test(D,D1)


boxplot(roughHigh, roughLow, A,A1, B,B1, C,C1, D,D1,
        at=c(1,2, 4,5, 7,8, 10,11 ,13,14), xaxt="n", ylab="Topic Entropy", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5,10.5,13.5),  labels= c("TPS vs DS","Q1","Q2","Q3","Q4"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)
abline(v=9, lty=2)
abline(v=12, lty=2)

legend(locator(1),legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1.2, pch=c(0,15))



plot(density(roughHigh), type="l", pch=16, ylab="PDF", xlab="Readability", 
     lwd=2, lty=1, col="red", main = "")
lines(density(roughLow), type="l", pch=6, xaxt="n", yaxt="n", xlab="",lwd=2, lty=1,
      col="green")




#t.test(HighT,Low)
#cohen.d(HighT,Low)