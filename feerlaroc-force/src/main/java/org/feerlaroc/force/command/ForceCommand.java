package org.feerlaroc.force.command;

import org.feerlaroc.force.listener.ForceCompletionListener;

/**
 * Created by root on 2016/03/17.
 */
public interface ForceCommand {

    <T> void add(T t, final ForceCompletionListener listener);
    <T> void update(T t, final ForceCompletionListener listener);

    <T> void remove(T t, final ForceCompletionListener  listener);
    <T> void remove(String key, String id, final ForceCompletionListener  listener);

    <T> void get(T t, final ForceCompletionListener listener);

}
