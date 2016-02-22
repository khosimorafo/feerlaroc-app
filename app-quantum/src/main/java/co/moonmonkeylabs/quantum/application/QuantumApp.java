package co.moonmonkeylabs.quantum.application;

import android.support.v7.widget.RecyclerView;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.Services;
import com.feerlaroc.core.app.App;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.NetworkCompletionListener;
import com.feerlaroc.firebase.app.FirebaseApp;
import com.feerlaroc.firebase.command.AddFirebaseEntityCommand;
import com.feerlaroc.firebase.command.DeleteFirebaseEntityCommand;
import com.feerlaroc.firebase.command.FirebaseServiceCommand;

import java.io.IOException;

/**
 * Created by root on 2016/02/13.
 */
public class QuantumApp implements App, FirebaseApp {


    @Override
    public <T extends EntityInterface> void create(Class<T> type, T entity, NetworkCompletionListener listener)
            throws FrameworkException {

        AddFirebaseEntityCommand<T> command = command(AddFirebaseEntityCommand.class);
        command.addNetworkCompletionListener(listener);
        command.execute(entity);

    }

    @Override
    public <T extends EntityInterface> void remove(Class<T> type, T entity, NetworkCompletionListener listener)
            throws FrameworkException {

        DeleteFirebaseEntityCommand<T> command = command(DeleteFirebaseEntityCommand.class);
        command.addNetworkCompletionListener(listener);
        command.execute(entity);

    }

    @Override
    public <T extends EntityInterface> void remove(Class<T> type, String id, NetworkCompletionListener listener)
            throws FrameworkException {

        try {

            Class clazz = Services.getInstance().getEntityClassForName(type.getSimpleName());
            EntityInterface entity = (EntityInterface) clazz.newInstance();

            DeleteFirebaseEntityCommand<T> command = command(DeleteFirebaseEntityCommand.class);

            command.addNetworkCompletionListener(listener);
            command.execute(id, entity.DBKey());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public <T extends EntityInterface> T retrieve(Class<T> type, String id, NetworkCompletionListener listener)
            throws FrameworkException {

        T model = null;

        return model;

    }

    @Override
    public <T extends Command> T command(Class<T> commandType) {
        return Services.getInstance().command(commandType);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public FirebaseServiceCommand adapter(RecyclerView.Adapter adapter) {
        return null;
    }
}
