package entity;

public class Memory {
    //序号
    int id=0;
    //优先级
    int priority;
    //下次访问的距离
    int distance;

    public Memory() {
    }

    public Memory(int id, int priority, int distance) {
        this.id = id;
        this.priority = priority;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "id=" + id +
                ", priority=" + priority +
                ", distance=" + distance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
