import esprima  

import os


folderLocation = 'E:/Projects/SOContentQualityResources/DataStore/JavaScript/CodeReadability/Code_Score_GT_0/'
csvWriter = open('E:/Projects/SOContentQualityResources/FeatureValues/TextCode/JavaScript/Usability/Parsability/Usability_Score_GT_0.csv',"w")
header = "Id ,Parsability \n"
csvWriter.write(header)

for filename in os.listdir(folderLocation):
    f = open(folderLocation+filename,"r")
    pyCode = f.read()
    
    try:
        code = esprima.parseScript(pyCode)
        parsabilityStatus = 1
        #print("Yes")
    except:
        parsabilityStatus = 0
        #print("No")
    
    questionId = filename.split('.')
    #print(questionId[0])
    
    result = str(questionId[0]) + "," + str(parsabilityStatus) + "\n"
    
    csvWriter.write(result)
    
    print(str(parsabilityStatus)+"\n")
    
f.close()
csvWriter.close()