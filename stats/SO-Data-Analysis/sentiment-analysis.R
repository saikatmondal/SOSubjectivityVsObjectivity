
library(effsize)

graphics.off()
par(mfrow=c(2,2))

readabilityHighT=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/Sentiment/Sentiment_Score_GT_0.csv", header = T)
#readabilityHigh=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/Sentiment/Sentiment_Score_GT_0_Random_Sample.csv", header = T)
readabilityLow=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/Sentiment/Sentiment_Score_LT_0.csv", header = T)


#High Total
nreadHigh=readabilityHighT$Score
summary(nreadHighT)

#High Random Sample
#nreadHigh=readabilityHigh$Score
#summary(nreadHigh)

#Low
nreadLow=readabilityLow$Score
summary(nreadLow)


wilcox.test(nreadHigh,nreadLow)
cliff.delta(nreadHigh,nreadLow)


A=nreadHigh[(nreadHigh>=0 & nreadHigh < quantile(nreadHigh,0.25))]
B=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.25) & nreadHigh < quantile(nreadHigh,0.50))]
C=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.50) & nreadHigh < quantile(nreadHigh,0.75))]
D=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.75) & nreadHigh <= quantile( nreadHigh,1.0))]


A1=nreadLow[(nreadLow>=0 & nreadLow < quantile(nreadLow,0.25))]
B1=nreadLow[(nreadLow>=quantile(nreadLow,0.25) & nreadLow < quantile(nreadLow,0.50))]
C1=nreadLow[(nreadLow>=quantile(nreadLow,0.50) & nreadLow < quantile(nreadLow,0.75))]
D1=nreadLow[(nreadLow>=quantile(nreadLow,0.75) & nreadLow <= quantile( nreadLow,1.0))]



boxplot(nreadHighT, nreadLow, nreadHigh, nreadLow,A,A1,B,B1,C,C1,D,D1,
        at=c(1,2, 4,5, 7,8,9,10,11,12,13,14), xaxt="n", ylab="Readability", outline = F,cex.lab=1.3,
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5,9.5,11.5,13.5),  labels= c("TPS vs DS","RPS vs DS","Q1","Q2","Q3","Q4"), cex.axis = 1.3)

abline(v=3, lty=2)
abline(v=6, lty=2)

legend("bottomright",legend= c( "Promoted","Discouraged"), col=c("black","gray"), cex=1.2, pch=c(0,15))



#Making Color
mycol = rgb(0, 0, 50, max = 255, alpha = 130, names = "blue50")

#High Total vs Low

plot (density(nreadHighT), type="l", pch=16, lwd=2, lty=1,ylab="Density", xlab="Readability", col="gray", cex.lab=1.5, main = "")

polygon(density(nreadHighT), col="gray", border="gray")

lines (density(nreadLow), type="l", pch=16 ,lwd=2, lty=1, ylab="Density", xlab="Readability",
       col=mycol)

polygon(density(nreadLow), col=mycol, border="black")

legend("topright", legend=c("Promoted", "Discouraged"),
       fill=c("gray", mycol), lty=1, lwd=2, cex=1.5)




#High Random Sample vs Low
plot (density(nreadHigh), type="l", pch=16, lwd=2, lty=1,ylab="Density", xlab="Readability", col="gray",cex.lab=1.5, main = "")

polygon(density(nreadHigh), col="gray", border="gray")

lines (density(nreadLow), type="l", pch=16 ,lwd=2, lty=1, ylab="Density", xlab="Readability",
       col=mycol)

polygon(density(nreadLow), col=mycol, border="black")

legend("topright", legend=c("Promoted", "Discouraged"),
       fill=c("gray", mycol), lty=1, lwd=2, cex=1.5)


wilcox.test(nreadHigh,nreadLow)
cliff.delta(nreadHigh,nreadLow)

wilcox.test(A,A1)
cliff.delta(A,A1)

wilcox.test(B,B1)
cliff.delta(B,B1)

wilcox.test(C,C1)
cliff.delta(C,C1)

wilcox.test(D,D1)
cliff.delta(D,D1)



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


