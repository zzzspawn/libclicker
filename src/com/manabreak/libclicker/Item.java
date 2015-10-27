/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manabreak.libclicker;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Base class for all the purchasable "items".
 * 
 * @author Harri Pellikka
 */
public abstract class Item
{
    /**
     * The base price of the item (i.e. the price of the first level of this item)
     */
    protected BigInteger m_basePrice = BigInteger.ONE;
    
    /**
     * Name of this item
     */
    protected String m_name = "Nameless Item";
    
    /**
     * Description text for this item
     */
    protected String m_description = "No description.";
    
    /**
     * Current level of this item
     */
    protected long m_itemLevel = 0;
    
    /**
     * Max. item level
     */
    protected long m_maxItemLevel = Long.MAX_VALUE;
    
    /**
     * Price multiplier per level. This is used in the price formula
     * like this: price = (base price) * (price multiplier) ^ (item level)
     */
    protected double m_priceMultiplier = 1.145;
    
    /**
     * World this item belongs to
     */
    private final World m_world;
    
    /**
     * Modifiers applied to this item
     */
    final ArrayList<Modifier> m_modifiers = new ArrayList<>();
    
    /**
     * Constructs a new item
     */
    protected Item(World world)
    {
        m_world = world;
    }
    
    /**
     * Constructs a new item
     * @param name Name of this item
     */
    protected Item(World world, String name)
    {
        m_world = world;
        setName(name);
    }
    
    /**
     * Retrieves the name of this item
     * @return Name of this item 
     */
    public String getName()
    {
        return m_name;
    }
    
    /**
     * Sets the name of this item
     * @param name New name for this item
     */
    public void setName(String name)
    {
        if(name == null || name.isEmpty()) throw new RuntimeException("Item name cannot be null or empty");
        m_name = name;
    }
    
    public String getDescription()
    {
        return m_description;
    }
    
    public void setDescription(String description)
    {
        m_description = description;
    }
    
    /**
     * Retrieves the base price of this item
     * @return Base price of this item
     */
    public BigInteger getBasePrice()
    {
        return m_basePrice;
    }
    
    /**
     * Sets the base price of this item
     * @param basePrice New base price for this item
     */
    public void setBasePrice(BigInteger basePrice)
    {
        if(basePrice == null) throw new RuntimeException("Base price cannot be null");
        if(basePrice.equals(BigInteger.ZERO)) throw new RuntimeException("Base price cannot be zero");
        m_basePrice = basePrice;
    }
    
    public void setBasePrice(long basePrice)
    {
        m_basePrice = new BigInteger("" + basePrice);
    }
    
    public void setBasePrice(int basePrice)
    {
        m_basePrice = new BigInteger("" + basePrice);
    }
    
    /**
     * Retrieves the price multiplier
     * @return Price multiplier
     */
    public double getPriceMultiplier()
    {
        return m_priceMultiplier;
    }
    
    /**
     * Sets the price multiplier of this item
     * @param multiplier Price multiplier
     */
    public void setPriceMultiplier(double multiplier)
    {
        m_priceMultiplier = multiplier;
    }
    
    public long getMaxItemLevel()
    {
        return m_maxItemLevel;
    }
    
    public void setMaxItemLevel(long maxLvl)
    {
        if(maxLvl <= 0) throw new RuntimeException("Max item level cannot be zero or negative");
        m_maxItemLevel = maxLvl;
    }
    
    public long getItemLevel()
    {
        return m_itemLevel;
    }
    
    public void setItemLevel(long lvl)
    {
        if(lvl < 0) throw new RuntimeException("Item level cannot be negative");
        if(lvl > m_maxItemLevel) throw new RuntimeException("Item level cannot be greater than max. item level");
        m_itemLevel = lvl;
    }
    
    public void upgrade()
    {
        if(m_itemLevel < m_maxItemLevel)
        {
            m_itemLevel++;
        }
    }
    
    public void downgrade()
    {
        if(m_itemLevel > 0)
        {
            m_itemLevel--;
        }
    }
    
    public void maximize()
    {
        m_itemLevel = m_maxItemLevel;
    }
}