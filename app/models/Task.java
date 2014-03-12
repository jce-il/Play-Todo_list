package models;

import java.io.Console;
import java.lang.Exception;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Task extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String label;

    public boolean done;

    public String owner;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date dueDate = new Date();

    public static Finder<Long,Task> find = new Finder<Long,Task>(
            Long.class, Task.class
    );

    public static List<Task> all() {
        return find.all();
    }

    public static List<Task> notDone() {
        List<Task> ret = new ArrayList<models.Task>();
        List<Task> all = find.all();
        for (int i =0; i< all.size(); i++)
        {
            Task curr =all.get(i);
            if (!curr.done)
            {
                ret.add(ret.size(),curr);
            }
        }

        return ret;
    }

    public static List<Task> Done() {
        List<Task> ret = new ArrayList<models.Task>();
        List<Task> all = find.all();
        for (int i =0; i< all.size(); i++)
        {
            Task curr =all.get(i);
            if (curr.done)
            {
                ret.add(ret.size(),curr);
            }
        }

        return ret;
    }

    public static void create(Task task) {
        task.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();

    }

    public static void markAsDone(Long id) {
        Task a = find.ref(id);
        a.done = true;
        a.save();


    }

    public static void markAsUnDone(Long id) {
        Task a = find.ref(id);
        a.done = false;
        a.save();
    }



}