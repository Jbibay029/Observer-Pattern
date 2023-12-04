import java.util.ArrayList;
import java.util.List;

interface NewsPublisher {
    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);

    void notifySubscribers(String news);
}

class ConcreteNewsAgency implements NewsPublisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String news) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }

    public void publishNews(String news) {
        System.out.println("Breaking News: " + news);
        notifySubscribers(news);
    }
}

interface Subscriber {
    void update(String news);
}

class ConcreteSubscriber implements Subscriber {
    private String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received breaking news: " + news);
    }
}

public class NewsSubscriptionSystem {
    public static void main(String[] args) {
        ConcreteNewsAgency newsAgency = new ConcreteNewsAgency();

        Subscriber subscriber1 = new ConcreteSubscriber("Subscriber 1");
        Subscriber subscriber2 = new ConcreteSubscriber("Subscriber 2");

        newsAgency.subscribe(subscriber1);
        newsAgency.subscribe(subscriber2);

        newsAgency.publishNews("Important Event");

        // Unsubscribe one subscriber
        newsAgency.unsubscribe(subscriber1);

        newsAgency.publishNews("Another Breaking News");
    }
}