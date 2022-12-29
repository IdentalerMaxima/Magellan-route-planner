package com.magellan.route;

import com.magellan.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@ApplicationScoped
public class RouteService {

    public String convertRouteToJsonString(Route route){
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
    }

    public String convertCommentToJsonString(Comment comment){
        return String.format("{\"id\":\"%d\"," +
                        "\"username\":\"%s\"," +
                        "\"routeId\":\"%d\"," +
                        "\"comment\":\"%s\"," +
                        "\"rating\":\"%d\"}",
                        comment.getId(), comment.getUserName(), comment.getRouteId(),
                        comment.getComment(), comment.getRating());
    }

    private int getAverage(List<Integer> numbers){
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
