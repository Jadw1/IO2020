package com.io2020.entities;

import com.io2020.map.MapObject;
import com.io2020.map.TileEntity;

public class Resource implements MapObject {

    TileEntity[] tileEntities;

    @Override
    public TileEntity[] getTileEntities() {
        return tileEntities;
    }

    public void setTileEntities(TileEntity[] tileEntities) {
        this.tileEntities = tileEntities;
    }
}
