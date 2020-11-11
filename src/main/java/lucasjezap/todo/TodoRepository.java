package lucasjezap.todo;

import lucasjezap.HibernateUtil;
import java.util.List;

class TodoRepository {

    List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var todo = session.get(Todo.class, id);
        todo.setDone(!todo.isDone());

        transaction.commit();
        session.close();
        return todo;
    }

    Todo addTodo(Todo newTodo) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;
    }
}
