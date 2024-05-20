package com.scramble_like.game.essential.chaos;

import com.scramble_like.game.essential.CoreConstant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SpatialGrid
{
    ////////// Attributes //////////
    private final HashMap<Integer, HashMap<Integer, Set<Collider>>> grid;

    ////////// Constructor //////////
    public SpatialGrid(int size)
    {
        grid = new HashMap<>();
        for (int i = 0; i < size; i++)
        {
            grid.put(i, new HashMap<>());
            for (int j = 0; j < size; j++)
            {
                grid.get(i).put(j, null);
            }
        }
    }

    public SpatialGrid(int sizeX, int sizeY)
    {
        grid = new HashMap<>();
        for (int i = 0; i < sizeX; i++)
        {
            grid.put(i, new HashMap<>());
            for (int j = 0; j < sizeY; j++)
            {
                grid.get(i).put(j, null);
            }
        }
    }

    ////////// Methods //////////
    private int getCellIndex(float coord) { return (int) Math.floor((coord - (double) CoreConstant.SPATIAL_GRID_MIN_VALUE_AXIS) / CoreConstant.SPATIAL_GRID_SIZE); }

    public void addCollider(Collider collider)
    {
        int xIndex = getCellIndex(collider.getOwnerX());
        int yIndex = getCellIndex(collider.getOwnerY());
        grid.computeIfAbsent(xIndex, k -> new HashMap<>()).computeIfAbsent(yIndex, k -> new HashSet<>()).add(collider);
    }

    public void addTileCollider(TileCollider tileCollider)
    {
        int xIndex = getCellIndex(tileCollider.getX1() + tileCollider.getWidth() / 2);
        int yIndex = getCellIndex(tileCollider.getY1() + tileCollider.getHeight() / 2);
        grid.computeIfAbsent(xIndex, k -> new HashMap<>()).computeIfAbsent(yIndex, k -> new HashSet<>()).add(tileCollider);
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
                    potentialColliders.addAll(grid.get(xIndex + i).get(yIndex + j));
                }
            }
        }
        return potentialColliders;
    }

    public void clear()
    {
        for (int i = 0; i < grid.size(); i++)
        {
            grid.get(i).clear();
        }
        grid.clear();
    }
}
