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
        if (collider.inGrid) { return; }
        collider.gridCellX = getCellIndex(collider.getGridX());
        collider.gridCellY = getCellIndex(collider.getGridY());
        grid.computeIfAbsent(collider.gridCellX, k -> new ConcurrentHashMap<>()).computeIfAbsent(collider.gridCellY, k -> new ArrayList<>()).add(collider);
        collider.inGrid = true;
    }

    public void removeCollider(Collider collider)
    {
        if (!collider.inGrid) { return; }
        collider.inGrid = false;
        ConcurrentHashMap<Integer, ArrayList<Collider>> column = grid.get(collider.gridCellX);
        if (column == null) { return; }
        ArrayList<Collider> cell = column.get(collider.gridCellY);
        if (cell == null) { return; }
        cell.remove(collider);
        if (cell.isEmpty())
        {
            column.remove(collider.gridCellY);
            if (column.isEmpty()) { grid.remove(collider.gridCellX); }
        }
    }

    public void updateCollider(Collider collider)
    {
        if (!collider.inGrid) { return; }
        if (collider.gridCellX == getCellIndex(collider.getGridX()) && collider.gridCellY == getCellIndex(collider.getGridY())) { return; }
        removeCollider(collider);
        addCollider(collider);
    }

    public Set<Collider> getPotentialCollisions(Collider collider)
    {
        Set<Collider> potentialColliders = new HashSet<>();
        int xIndex = getCellIndex(collider.getGridX());
        int yIndex = getCellIndex(collider.getGridY());

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

    public int countEntries()
    {
        int count = 0;
        for (ConcurrentHashMap<Integer, ArrayList<Collider>> column : grid.values())
        {
            for (ArrayList<Collider> cell : column.values()) { count += cell.size(); }
        }
        return count;
    }

    public int countStaleEntries()
    {
        int stale = 0;
        Set<Collider> seen = new HashSet<>();
        for (ConcurrentHashMap<Integer, ArrayList<Collider>> column : grid.values())
        {
            for (ArrayList<Collider> cell : column.values())
            {
                for (Collider c : cell) { if (c.getOwner() == null || !seen.add(c)) { stale++; } }
            }
        }
        return stale;
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
