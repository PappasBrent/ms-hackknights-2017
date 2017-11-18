import re, math

line = "1926 On ch=1 n=60 v=114"

trackPattern = r"(\d+) On ch=(\d+) n=(\d+) v=(\d+)"

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
    # print(vals[0])
    if len(vals[0]) < 4:
        continue

    t=int(vals[0][0])
    ch=int(vals[0][1])
    n=int(vals[0][2])
    v=int(vals[0][3])

    octave = math.floor(n/12)
    pVal = n%12

    notes+=possibleNotes[pVal]

print(notes)

outfile.write(notes)

midiText.close()
outfile.close()
