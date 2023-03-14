

featuresTextOnly = read.csv("E:/Projects/SOContentQualityResources/FeatureValues/Case-C(Both)/Combined/TR.csv", header = T)

cor.test(featuresTextOnly$TextReadability, featuresTextOnly$QuestionType, method=c("pearson", "kendall", "spearman"))

cor.test(featuresTextOnly$TextReadability, featuresTextOnly$QuestionType, method=c("pearson", "kendall", "spearman"))

cor.test(featuresTextOnly$TagEntropy, featuresTextOnly$QuestionType, method=c("pearson", "kendall", "spearman"))

chisq.test(featuresTextOnly$Sentiment, featuresTextOnly$QuestionType)

FSelector