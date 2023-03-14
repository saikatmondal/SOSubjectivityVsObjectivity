
library(effsize)

rougeHigh=read.table("ROUGE/Rouge-High.txt", header = T)
summary(rougeHigh)

rougeLow=read.table("ROUGE/Rouge-Low.txt", header = T)
summary(rougeLow)

par(mfrow=c(1,2))
#par(mgp=c(2,0.7,0), mar=c(7.1,4.1,1.1,2.1))

#boxplot(readabilityHigh$Score, readabilityLow$Score, names=c("Promoted","Discouraged"))

#(density(readabilityHigh$Score), type="b",  pch=3,  col="green", ylab="PDF", xlab="Readability",  
#     ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#     lwd=2 )
#lines(density(readabilityLow$Score), pch=5,  col="red", xlab=" ", ylab=" ",  ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#      lwd=2)

plot(density(rougeHigh$Precision), type="l", pch=16, ylab="PDF", xlab="L-Rouge:Precision",
     lwd=2, lty=1, col="green", main = "")
lines(density(rougeLow$Precision), type="l", pch=6, xaxt="n", yaxt="n", xlab="",lwd=2, lty=1,
      col="red")


boxplot(rougeHigh$Precision, rougeLow$Precision, border=c("green","red"), 
        ylab="L-Rouge:Preicision",
        lwd=2, names=c("Promoted","Discouraged"))






