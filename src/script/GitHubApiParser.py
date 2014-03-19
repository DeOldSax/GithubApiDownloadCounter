from urllib import request
import json
import datetime
import csv
import os
import sys

apiUrl = sys.argv[1]
htmlContent = ""

for line in request.urlopen(apiUrl):
    htmlContent += line.decode('utf-8')
    
jsonA = json.loads(htmlContent)

versionList = [""]
downloadList = [datetime.date.today()]
i = 0

for a in jsonA:
	print(a["name"].lower().replace(" ", ""))
	versionList.append(a["name"].lower().replace(" ", ""))
	downloadList.append(a["assets"][0]["download_count"])

csvFile = "downloads.csv"

if not os.path.exists(csvFile):
	open(csvFile, "a").close()

j = 0
oldDownloadRows = []
with open(csvFile, newline="") as f:
	reader = csv.reader(f, delimiter = "|")
	for row in reader:
		j = j + 1
		if j != 1:
			oldDownloadRows.append(row)

with open(csvFile, "w", newline="") as f:
	c = csv.writer(f, delimiter = "|")
	# first row with versions	
	c.writerow(versionList)
	# append rows
	c.writerows(oldDownloadRows + [downloadList])
input()
