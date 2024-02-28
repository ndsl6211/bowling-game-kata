package tw.idv.mashu.bowling;

import java.util.Optional;

public class Frame {

    private Optional<Integer> firstRolledPin;
    private Optional<Integer> secondRolledPin;
    private Optional<Integer> extraFirstRolledPin;
    private Optional<Integer> extraSecondRolledPin;

    public Frame() {
        this.firstRolledPin = Optional.empty();
        this.secondRolledPin = Optional.empty();
        this.extraFirstRolledPin = Optional.empty();
        this.extraSecondRolledPin = Optional.empty();
    }

    public int getScore() {
        return this.firstRolledPin.orElse(0) +
                this.secondRolledPin.orElse(0) +
                this.extraFirstRolledPin.orElse(0) +
                this.extraSecondRolledPin.orElse(0);
    }

    public boolean isStrike() {
        return this.firstRolledPin.isPresent() && this.firstRolledPin.get() == 10;
    }

    public boolean isTwoRollsCompleted() {
        return this.firstRolledPin.isPresent() && this.secondRolledPin.isPresent();
    }

    public boolean isSpare() {
        return this.isTwoRollsCompleted() && this.firstRolledPin.get() + this.secondRolledPin.get() == 10;
    }

    public void setRolledPin(int pin) {
        if (this.firstRolledPin.isEmpty()) this.firstRolledPin = Optional.of(pin);
        else if (this.secondRolledPin.isEmpty() && !this.isStrike()) this.secondRolledPin = Optional.of(pin);
        else if (this.extraFirstRolledPin.isEmpty()) this.extraFirstRolledPin = Optional.of(pin);
        else if (this.extraSecondRolledPin.isEmpty()) this.extraSecondRolledPin = Optional.of(pin);
    }

    public boolean isFrameFinished() {
        return this.isStrike() || this.isTwoRollsCompleted();
    }

    public void setExtraFirst(int pin) {
        this.extraFirstRolledPin = Optional.of(pin);
    }

    public void setExtraSecond(int pin) {
        this.extraSecondRolledPin = Optional.of(pin);
    }

    public int getExtraFirst() {
        return this.extraFirstRolledPin.get();
    }

    public int getFirst() {
        return this.firstRolledPin.get();
    }

    public int getSecond() {
        return this.secondRolledPin.get();
    }

    public String toString() {
        return "first: " + this.firstRolledPin +
                ", second: " + this.secondRolledPin +
                ", extra first: " + this.extraFirstRolledPin +
                ", extra second: " + this.extraSecondRolledPin;
    }
}
