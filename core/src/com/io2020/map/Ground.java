package com.io2020.map;

public class Ground implements MapObject {
    TileEntity[] tileEntities;

    @Override
    public TileEntity[] getTileEntities() {
        return tileEntities;
    }

    public void setTileEntities(TileEntity[] tileEntities) {
        this.tileEntities = tileEntities;
    }
}
