
library(effsize)

graphics.off()
par(mfrow=c(1,2))

readCommentAnswerH=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/CommentAnswerCount/Commment_Answer_Count_Score_GT_0.csv", header = T)
readCommentAnswerL=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/CommentAnswerCount/Commment_Answer_Count_Score_LT_0.csv", header = T)

readabilityHigh=read.csv("E:/Projects/SOContentQualityResources/CSVData/Java/MetricResults/TextOnlyFile/TextReadability/Text_Readability_Score_GT_0.csv", header = T)


commentHigh=readCommentAnswerH$No_of_Comment
#commentHigh = sample(commentHigh, 20)
summary(commentHigh)

commentLow=readCommentAnswerL$No_of_Comment
summary(commentLow)

answerHigh = readCommentAnswerH$No_of_Answer
#summary(answerHig)

answerLow = readCommentAnswerL$No_of_Answer
#summary(answerHig)

readabilityHigh=readabilityHigh$Score
#readabilityHigh = sample(readabilityHigh, 20)
#summary(readabilityHigh)



cor.test(readabilityHigh,answerHigh)
cor.test(answerHigh, commentHigh, method=c("pearson"))
cor.test(readabilityHigh, commentHigh, method=c("spearman"))
cor.test(readabilityHigh, answerHigh, method=c("kendall"))
cor.test(readabilityHigh, answerHigh, method=c("spearman"))


wilcox.test(commentHigh,commentLow)
cliff.delta(commentHigh,commentLow)

wilcox.test(answerHigh,answerLow)
cliff.delta(answerHigh,answerLow)

plot(readabilityHigh$Id, readabilityHigh$Score)

plot(density(commentHigh), type="l", pch=16, ylab="PDF", xlab="Comment", 
     lwd=2, lty=1, col="red", main = "")
lines(density(readabilityHigh), type="l", pch=6, xaxt="n", yaxt="n", xlab="",lwd=2, lty=1,
      col="green")


boxplot(commentHigh, commentLow, border=c("green","red"), ylab="Comment", outline = F,
        
        lwd=2, names=c("Promoted","Discouraged"))



plot(density(answerHigh), type="l", pch=16, ylab="PDF", xlab="Answer", 
     lwd=2, lty=1, col="red", main = "")
lines(density(readabilityHigh), type="l", pch=6, xaxt="n", yaxt="n", xlab="",lwd=2, lty=1,
      col="green")


boxplot(answerHigh, answerLow, border=c("green","red"), ylab="Answer", outline = F,
        
        lwd=2, names=c("Promoted","Discouraged"))


