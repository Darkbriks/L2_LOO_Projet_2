package com.scramble_like.game.essential.chaos;

import com.scramble_like.game.essential.CoreConstant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SpatialGrid
{
    ////////// Attributes //////////
    private final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, ArrayList<Collider>>> grid;

    ////////// Constructor //////////
    public SpatialGrid(int size)
    {
        grid = new ConcurrentHashMap<>();
        for (int i = 0; i < size; i++)
        {
            grid.put(i, new ConcurrentHashMap<>());
        }
    }

    public SpatialGrid(int sizeX, int sizeY)
    {
        grid = new ConcurrentHashMap<>();
        for (int i = 0; i < sizeX; i++)
        {
            grid.put(i, new ConcurrentHashMap<>());
        }
    }

    ////////// Methods //////////
    private int getCellIndex(float coord) { return (int) Math.floor((coord - (double) CoreConstant.SPATIAL_GRID_MIN_VALUE_AXIS) / CoreConstant.SPATIAL_GRID_SIZE); }

    public void addCollider(Collider collider)
    {
        int xIndex = getCellIndex(collider.getOwnerX());
        int yIndex = getCellIndex(collider.getOwnerY());
        grid.computeIfAbsent(xIndex, k -> new ConcurrentHashMap<>()).computeIfAbsent(yIndex, k -> new ArrayList<>()).add(collider);
    }

    public void addTileCollider(TileCollider tileCollider)
    {
        int xIndex = getCellIndex(tileCollider.getX1() + tileCollider.getWidth() / 2);
        int yIndex = getCellIndex(tileCollider.getY1() + tileCollider.getHeight() / 2);
        grid.computeIfAbsent(xIndex, k -> new ConcurrentHashMap<>()).computeIfAbsent(yIndex, k -> new ArrayList<>()).add(tileCollider);
    }

    public void removeCollider(Collider collider)
    {
        int xIndex = getCellIndex(collider.getOwnerX());
        int yIndex = getCellIndex(collider.getOwnerY());
        if (grid.containsKey(xIndex) && grid.get(xIndex).containsKey(yIndex))
        {
            if (grid.get(xIndex).get(yIndex) == null) { return; }
            grid.get(xIndex).get(yIndex).remove(collider);
            if (grid.get(xIndex).get(yIndex).isEmpty())
            {
                grid.get(xIndex).remove(yIndex);
                if (grid.get(xIndex).isEmpty()) { grid.remove(xIndex); }
            }
        }
    }

    public void removeTileCollider(TileCollider tileCollider)
    {
        int xIndex = getCellIndex(tileCollider.getX1() + tileCollider.getWidth() / 2);
        int yIndex = getCellIndex(tileCollider.getY1() + tileCollider.getHeight() / 2);
        if (grid.containsKey(xIndex) && grid.get(xIndex).containsKey(yIndex))
        {
            grid.get(xIndex).get(yIndex).remove(tileCollider);
            if (grid.get(xIndex).get(yIndex).isEmpty())
            {
                grid.get(xIndex).remove(yIndex);
                if (grid.get(xIndex).isEmpty()) { grid.remove(xIndex); }
            }
        }
    }

    public void updateCollider(Collider collider) { removeCollider(collider); addCollider(collider); }

    public Set<Collider> getPotentialCollisions(Collider collider)
    {
        Set<Collider> potentialColliders = new HashSet<>();
        int xIndex = getCellIndex(collider.getOwnerX());
        int yIndex = getCellIndex(collider.getOwnerY());

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (grid.containsKey(xIndex + i) && grid.get(xIndex + i).containsKey(yIndex + j))
                {
                    if (grid.get(xIndex + i).get(yIndex + j) == null) { continue; }
                    //potentialColliders.addAll(grid.get(xIndex + i).get(yIndex + j));
                    for (int k = 0; k < grid.get(xIndex + i).get(yIndex + j).size(); k++)
                    {
                        potentialColliders.add(grid.get(xIndex + i).get(yIndex + j).get(k));
                    }
                }
            }
        }
        return potentialColliders;
    }

    public void clear()
    {
        for (int i = 0; i < grid.size(); i++)
        {
            if (grid.get(i) == null) { continue; }
            grid.get(i).clear();
        }
        grid.clear();
    }
}
