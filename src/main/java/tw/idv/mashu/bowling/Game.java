package tw.idv.mashu.bowling;

import java.util.ArrayList;

public class Game {

    private final ArrayList<Frame> _frames;
    private int _currentFrameIdx;

    public Game(int frameCount) {
        this._frames = new ArrayList<Frame>() {{
            for (int i = 0; i < frameCount; i++) {
                add(new Frame());
            }
        }};
        _currentFrameIdx = 0;
    }

    public void roll(int pin) {
        Frame currentFrame = this.getCurrentFrame();
        currentFrame.setRolledPin(pin);
        if (currentFrame.isFrameFinished()) this._currentFrameIdx++;
    }

    private Frame getCurrentFrame() {
        if (this._currentFrameIdx >= this._frames.size()) {
            return this._frames.get(this._frames.size() - 1);
        } else {
            return this._frames.get(this._currentFrameIdx);
        }
    }

    public int score() {
        for (int i = this._frames.size() - 2; i >= 0; i--) {
            Frame currentFrame = this._frames.get(i);
            Frame nextFrame = this._frames.get(i + 1);

            if (currentFrame.isSpare()) {
                currentFrame.setExtraFirst(nextFrame.getFirst());
            } else if (currentFrame.isStrike()) {
                if (nextFrame.isStrike()) {
                    currentFrame.setExtraFirst(nextFrame.getFirst());
                    currentFrame.setExtraSecond(nextFrame.getExtraFirst());
                } else {
                    currentFrame.setExtraFirst(nextFrame.getFirst());
                    currentFrame.setExtraSecond(nextFrame.getSecond());
                }
            }
        }

        return this._frames
                .stream()
                .mapToInt(Frame::getScore)
                .reduce(0, Integer::sum);
    }
}
