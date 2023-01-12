package com.magellan.route;

import com.magellan.user.User;
import com.magellan.user.UserController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IllegalFormatException;
import java.util.List;

@ApplicationScoped
public class RouteService {

    private static final Logger logger = LogManager.getLogger(RouteService.class);

    public String convertRouteToJsonString(Route route){
        try {
            return String.format("{\"id\":\"%d\"," +
                            "\"name\":\"%s\"," +
                            "\"length\":\"%d\"," +
                            "\"duration\":\"%d\"," +
                            "\"elevation\":\"%d\"," +
                            "\"image\":\"%s\"," +
                            "\"description\":\"%s\"," +
                            "\"routeDescription\":\"%s\"," +
                            "\"rating\":\"%d\"," +
                            "\"difficultyLevel\":\"%s\"}",
                    route.getId(), route.getName(), route.getLength(),
                    route.getDuration(), route.getElevation(), route.getImages(),
                    route.getDescription(), route.getRouteDescription(), getRating(route.getId()), route.getDifficultyLevel());
        }catch(IllegalFormatException e){
            logger.error("Formai hiba a konvertálás során!", e);
            return null;
        }
    }

    public String convertCommentToJsonString(Comment comment) {
        try {
            return String.format("{\"id\":\"%d\"," +
                            "\"username\":\"%s\"," +
                            "\"routeId\":\"%d\"," +
                            "\"comment\":\"%s\"," +
                            "\"rating\":\"%d\"}",
                    comment.getId(), comment.getUserName(), comment.getRouteId(),
                    comment.getComment(), comment.getRating());
        } catch(IllegalFormatException e){
            logger.error("Formai hiba a konvertálás során!", e);
            return null;
        }
    }

    private int getAverage(List<Integer> numbers){
        if (numbers.size() == 0){
            return 0;
        }
        int s = 0;
        for(int num : numbers){
            s += num;
        }
        return s / numbers.size();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    private int getRating(int routeId){
        @SuppressWarnings("unchecked")
        List<Integer> ratings = entityManager.createQuery("SELECT c.rating FROM Comment c WHERE c.routeId= :id").setParameter("id", routeId).getResultList();
        return getAverage(ratings);
    }

    @Transactional
    public Route getRouteFromDatabase(int routeId){
        return entityManager.find(Route.class, routeId);
    }

    @Transactional
    public List<Route> getAllRoutesFromDatabase(){
        return entityManager.createQuery("SELECT r FROM Route r", Route.class).getResultList();
    }

    @Transactional
    public List<Comment> getAllCommentsFromDataBaseForRoute(int id){
        return entityManager.createQuery("SELECT c FROM Comment c WHERE c.routeId = :id", Comment.class).setParameter("id", id).getResultList();
    }

    @Transactional
    public void saveCommentToDatabase(Comment comment){
        entityManager.persist(comment);
    }
}
