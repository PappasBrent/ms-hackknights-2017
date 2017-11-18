import re, math

line = "1926 On ch=1 n=60 v=114"

trackPattern = r"[ch|n|v]=(\d+)"

print(re.findall(trackPattern, line))


midiText = open("jinglebells.txt", "r")

outfile = open("Notes.in", 'w')

possibleNotes = ['C', 'c', 'D', 'e', 'E', 'F', 'f', 'G', 'a', 'A', 'b', 'B']

notes = ''

lines = list(midiText.readlines())

for line in lines:
    vals = re.findall(trackPattern, line)
    if not vals:
        continue
    if len(vals) < 3:
        continue

    ch=int(vals[0])
    n=int(vals[1])
    v=int(vals[2])

    octave = math.floor(n/12)
    pVal = n%12

    notes+=possibleNotes[pVal]

print(notes)

outfile.write(notes)

midiText.close()
outfile.close()
