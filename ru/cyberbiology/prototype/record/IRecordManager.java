package cyberbiology.prototype.record;

import cyberbiology.prototype.IBot;

public interface IRecordManager
{
	int getBufferSize();

	int getFrameSavedCounter();

	int getFrameSkipSize();

	boolean isRecording();

	void startFrame();

	void startRecording();

	void stopFrame();

	boolean stopRecording();

	void writeBot(IBot bot, int x, int y);

	boolean haveRecord();

	void makeSnapShot();
}
