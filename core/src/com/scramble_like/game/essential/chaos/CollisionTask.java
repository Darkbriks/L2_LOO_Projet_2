package com.scramble_like.game.essential.chaos;

import java.util.HashSet;
import java.util.concurrent.RecursiveTask;
import java.util.Set;

public class CollisionTask extends RecursiveTask<Set<Collider>>
{
    private static final int THRESHOLD = 15;
    private final Set<Collider> colliders;
    private final Collider currentCollider;

    public CollisionTask(Set<Collider> colliders, Collider currentCollider)
    {
        this.colliders = colliders;
        this.currentCollider = currentCollider;
    }

    @Override
    protected Set<Collider> compute()
    {
        if (colliders.size() <= THRESHOLD) { return computeDirectly(); }
        else
        {
            int mid = colliders.size() / 2;
            Set<Collider> firstHalf = new HashSet<>();
            Set<Collider> secondHalf = new HashSet<>();
            int count = 0;
            for (Collider collider : colliders)
            {
                if (count < mid) { firstHalf.add(collider); }
                else { secondHalf.add(collider); }
                count++;
            }

            CollisionTask leftTask = new CollisionTask(firstHalf, currentCollider);
            CollisionTask rightTask = new CollisionTask(secondHalf, currentCollider);
            invokeAll(leftTask, rightTask);

            Set<Collider> result = new HashSet<>(leftTask.join());
            result.addAll(rightTask.join());
            return result;
        }
    }

    private Set<Collider> computeDirectly()
    {
        Set<Collider> result = new HashSet<>();
        for (Collider other : colliders)
        {
            if (other == null || other.getOwner() == null) { continue; }
            if (currentCollider != other && currentCollider.Collide(other)) { result.add(other); }
        }
        return result;
    }
}
