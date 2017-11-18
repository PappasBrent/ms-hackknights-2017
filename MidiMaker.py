from midiutil.MidiFile import MIDIFile
import random

# create your MIDI object
mf = MIDIFile(1)     # only 1 track
track = 0   # the only track

time = 192    # start at the beginning
mf.addTrackName(track, time, "Harmonized Track")
mf.addTempo(track, time, 220)

timesIn = open("Times.in", "r")
channelsIn = open("Channels.in", 'r')
notesIn = open("Notes.in", 'r')
volumesIn = open("Volumes.in", 'r')

times = timesIn.readlines()
channels = channelsIn.readlines()
notes = notesIn.readline()
volumes = volumesIn.readlines()


# Default values
time = 0
channel = 0
pitch = 0
duration = 1
volume = 100

print(len(times), len(channels), len(notes), len(volumes))

numSounds = len(times)

for soundNum in range(numSounds):
    time = int(times[soundNum])
    channel = int(channels[soundNum])
    # pitch = int(notes[soundNum])
    pitch = random.randrange(60,71)
    volume = int(volumes[soundNum])
    mf.addNote(track, channel, pitch, time, duration, volume)


# pitch = 60           # C4 (middle C)
# time = 0             # start on beat 0
# duration = 1         # 1 beat long
# mf.addNote(track, channel, pitch, time, duration, volume)

# write it to disk
with open("output.mid", 'wb') as outf:
    mf.writeFile(outf)

timesIn.close()
channelsIn.close()
notesIn.close()
volumesIn.close()
