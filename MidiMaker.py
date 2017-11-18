from midiutil.MidiFile import MIDIFile

# create your MIDI object
mf = MIDIFile(1)     # only 1 track
track = 0   # the only track

time = 0    # start at the beginning
mf.addTrackName(track, time, "Sample Track")
mf.addTempo(track, time, 120)


notesFile = open("Notes.out", 'r')


# add some notes
channel = 0
pitch = 0
duration = 1
volume = 100

time = 0

for char in notesFile.readline():
    mf.addNote(track, channel, ord(char), time, duration, volume)
    time+=1

# for i in range(128):
#     pitch = i
#     time = i
#     mf.addNote(track, channel, pitch, time, duration, volume)

# pitch = 60           # C4 (middle C)
# time = 0             # start on beat 0
# duration = 1         # 1 beat long
# mf.addNote(track, channel, pitch, time, duration, volume)

# pitch = 64           # E4
# time = 2             # start on beat 2
# duration = 1         # 1 beat long
# mf.addNote(track, channel, pitch, time, duration, volume)

# pitch = 67           # G4
# time = 4             # start on beat 4
# duration = 1         # 1 beat long
# mf.addNote(track, channel, pitch, time, duration, volume)

# write it to disk
with open("output.mid", 'wb') as outf:
    mf.writeFile(outf)
