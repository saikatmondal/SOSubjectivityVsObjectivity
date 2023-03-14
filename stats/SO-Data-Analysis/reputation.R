
library(effsize)

reputationH=read.csv("ReputationHigh.csv", header = T)
nrepuHigh=(reputationH$Reputation -  min(reputationH$Reputation)) / (max(reputationH$Reputation) - min(reputationH$Reputation))

summary(nrepuHigh)

reputationL=read.csv("ReputationLow.csv", header = T)
nrepuLow=(reputationL$Reputation -  min(reputationL$Reputation)) / (max(reputationL$Reputation) - min(reputationL$Reputation))
summary(nrepuLow)


par(mfrow=c(1,2))
#par(mgp=c(2,0.7,0), mar=c(7.1,4.1,1.1,2.1))

#boxplot(readabilityHigh$Score, readabilityLow$Score, names=c("Promoted","Discouraged"))

#(density(readabilityHigh$Score), type="b",  pch=3,  col="green", ylab="PDF", xlab="Readability",  
#     ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#     lwd=2 )

#lines(density(readabilityLow$Score), pch=5,  col="red", xlab=" ", ylab=" ",  ylim=range(readabilityHigh$Score, readabilityLow$Score), 
#      lwd=2)

plot(density(nrepuHigh), type="l", pch=16, xaxt="n", yaxt="n", 
     lwd=2, lty=1, col="green", main = "")

lines(density(nrepuLow), type="l", pch=6, ylab="PDF", xlab="Readability",lwd=2, lty=1,
      col="red")


#boxplot(nrepuHigh, nrepuLow, border=c("green","red"), ylab="Readability", outline = F,
#        lwd=2, names=c("Promoted","Discouraged"))


A=nrepuHigh[(nrepuHigh>=0 & nrepuHigh < quantile(nrepuHigh,0.25))]
B=nrepuHigh[(nrepuHigh>=quantile(nrepuHigh,0.25) & nrepuHigh < quantile(nrepuHigh,0.50))]
C=nrepuHigh[(nrepuHigh>=quantile(nrepuHigh,0.50) & nrepuHigh < quantile(nrepuHigh,0.75))]
D=nrepuHigh[(nrepuHigh>=quantile(nrepuHigh,0.75) & nrepuHigh <= quantile( nrepuHigh,1.0))]


A1=nrepuLow[(nrepuLow>=0 & nrepuLow < quantile(nrepuLow,0.25))]
B1=nrepuLow[(nrepuLow>=quantile(nrepuLow,0.25) & nrepuLow < quantile(nrepuLow,0.50))]
C1=nrepuLow[(nrepuLow>=quantile(nrepuLow,0.50) & nrepuLow < quantile(nrepuLow,0.75))]
D1=nrepuLow[(nrepuLow>=quantile(nrepuLow,0.75) & nrepuLow <= quantile( nrepuLow,1.0))]

boxplot(nrepuHigh, nrepuLow,A,A1,B,B1,C,C1,D,D1,border=c("green","red"), ylab="Readability", outline = F,
        lwd=2, at=c(1,2, 4,5, 7,8, 10,11, 13,14))



wilcox.test(D,D1)
cliff.delta(D,D1)


