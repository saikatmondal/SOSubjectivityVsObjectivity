
uratio=read.table("C:/My MSc/ThesisWorks/Mining_Software_Repository/CodeReview-ICSE2017/experiment/codereview/stats/stopword/uratio.txt")
nuratio=read.table("C:/My MSc/ThesisWorks/Mining_Software_Repository/CodeReview-ICSE2017/experiment/codereview/stats/stopword/nuratio.txt")

uratioall=read.table("C:/My MSc/ThesisWorks/Mining_Software_Repository/CodeReview-ICSE2017/experiment/codereview/stats/stopword/uratio-all.txt")
nuratioall=read.table("C:/My MSc/ThesisWorks/Mining_Software_Repository/CodeReview-ICSE2017/experiment/codereview/stats/stopword/nuratio-all.txt")


#summary(uratio$V1)
#summary(nuratio$V1)

A=uratio$V1[(uratio$V1>=0 & uratio$V1 < quantile(uratio$V1,0.25))]
B=uratio$V1[(uratio$V1>=quantile(uratio$V1,0.25) & uratio$V1 < quantile(uratio$V1,0.50))]
C=uratio$V1[(uratio$V1>=quantile(uratio$V1,0.50) & uratio$V1 < quantile(uratio$V1,0.75))]
D=uratio$V1[(uratio$V1>=quantile(uratio$V1,0.75) & uratio$V1 <= quantile( uratio$V1,1.0))]

nA=nuratio$V1[(nuratio$V1>=0 & nuratio$V1 < quantile(nuratio$V1,0.25))]
nB=nuratio$V1[(nuratio$V1>=quantile(nuratio$V1,0.25) & nuratio$V1 < quantile(nuratio$V1,0.50))]
nC=nuratio$V1[(nuratio$V1>=quantile(nuratio$V1,0.50) & nuratio$V1 < quantile(nuratio$V1,0.75))]
nD=nuratio$V1[(nuratio$V1>=quantile(nuratio$V1,0.75) & nuratio$V1 <= quantile(nuratio$V1,1.0))]


boxplot(uratio$V1, nuratio$V1, uratioall$V1, nuratioall$V1,A,nA,B,nB,C,nC,D,nD,
        at=c(1,2, 4,5, 7,8,9,10,11,12,13,14), xaxt="n", ylab="Stopword/Keyword Ratio", 
        col = c("white","gray","white","gray","white","gray","white","gray","white","gray","white","gray"))
axis(side=1,at=c(1.5,4.5,7.5,9.5,11.5,13.5),  labels= c("SW","SW+KW","Q1","Q2","Q3","Q4"))

abline(v=3, lty=2)
abline(v=6, lty=2)

legend("bottomright",legend= c( "Useful","Non-useful"), col=c("black","gray"), pch=c(0,15))


#Comparing stop word ratio
wilcox.test(uratio$V1, nuratio$V1)
cohensD(uratio$V1, nuratio$V1)

#Comparing stopword+keyword ratio
wilcox.test(uratioall$V1, nuratioall$V1)
cohensD(uratioall$V1, nuratioall$V1)

#Comparing between quartiles
wilcox.test(A,nA)
cohensD(A, nA)

wilcox.test(B,nB)
cohensD(B, nB)

wilcox.test(C,nC)
cohensD(C, nC)

wilcox.test(D,nD)
cohensD(D, nD)





library("ggplot2")

ggdensity(wdata, x = "weight",
          add = "mean", rug = TRUE,
          color = "sex", fill = "sex",
          palette = c("#0073C2FF", "#FC4E07"))


























