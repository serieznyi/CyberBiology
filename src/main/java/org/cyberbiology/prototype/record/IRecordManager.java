package org.cyberbiology.prototype.record;

import java.io.File;

import org.cyberbiology.prototype.IBot;

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

	//public void save(File selectedFile);

	boolean haveRecord();

	void makeSnapShot();

	//public void deleteRecord();

}
