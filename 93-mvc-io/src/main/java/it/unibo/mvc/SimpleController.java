package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {

    private List<String> historyStrings = new LinkedList<>();
    private String nextString;

    /**
     * {@inheritDoc}
     */
    @Override
    public void currentString() throws IllegalStateException {
        final String last = this.getNextString();
        if (!last.equals(null)) {
            IO.println(last);
            this.historyStrings.add(last);
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return this.nextString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> historyStrings() {
        return this.historyStrings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String string) throws NullPointerException {
        if (!string.equals(null)) {
            this.nextString = string;
        } else {
            throw new NullPointerException();
        }
    }

}
