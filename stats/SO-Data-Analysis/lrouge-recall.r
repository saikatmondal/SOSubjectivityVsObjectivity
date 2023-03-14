
library(effsize)

rougeHigh=read.csv("ROUGE_Score_1_to_5.csv", header = T)
summary(rougeHigh)

rougeLow=read.csv("ROUGE_Score_Minus_1_to_Minus_5.csv", header = T)
summary(rougeLow)

nreadHigh = sample(rougeHigh$Avg_Recall_L,299,replace = T)
nreadLow = rougeLow$Avg_Recall_L

par(mfrow=c(1,2))
#par(mgp=c(2,0.7,0), mar=c(7.1,4.1,1.1,2.1))

#boxplot(readabilityHigh$Score, readabilityLow$Score, names=c("Promoted","Discouraged"))

#(density(readabilityHigh$Score), type="b",  pch=3,  col="green", ylab="PDF", xlab="Readability",  
#     ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#     lwd=2 )
#lines(density(readabilityLow$Score), pch=5,  col="red", xlab=" ", ylab=" ",  ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#      lwd=2)

plot(density(nreadLow), type="l", pch=16, ylab="PDF", xlab="L-Rouge: Recall",
     lwd=2, lty=1, col="red", main = "")
lines(density(nreadHigh), type="l", pch=6, xaxt="n", yaxt="n", xlab="",lwd=2, lty=1,
      col="green")


#boxplot(rougeHigh$Avg_Recall_L, rougeLow$Avg_Recall_L, border=c("green","red"), 
#        ylab="L-Rouge:Recall", outline = F,
#        lwd=2, names=c("Promoted","Discouraged"))


A=nreadHigh[(nreadHigh>=0 & nreadHigh < quantile(nreadHigh,0.25))]
B=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.25) & nreadHigh < quantile(nreadHigh,0.50))]
C=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.50) & nreadHigh < quantile(nreadHigh,0.75))]
D=nreadHigh[(nreadHigh>=quantile(nreadHigh,0.75) & nreadHigh <= quantile( nreadHigh,1.0))]


A1=nreadLow[(nreadLow>=0 & nreadLow < quantile(nreadLow,0.25))]
B1=nreadLow[(nreadLow>=quantile(nreadLow,0.25) & nreadLow < quantile(nreadLow,0.50))]
C1=nreadLow[(nreadLow>=quantile(nreadLow,0.50) & nreadLow < quantile(nreadLow,0.75))]
D1=nreadLow[(nreadLow>=quantile(nreadLow,0.75) & nreadLow <= quantile( nreadLow,1.0))]

boxplot(nreadHigh, nreadLow,A,A1,B,B1,C,C1,D,D1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2, 4,5, 7,8, 10,11, 13,14))

wilcox.test(A,A1)
cliff.delta(A,A1)






