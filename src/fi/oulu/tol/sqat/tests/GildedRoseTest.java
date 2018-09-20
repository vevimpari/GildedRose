package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;

public class GildedRoseTest {

	// Sorry, I made getters and setters for items, couldn't come up with a better solution now 
	// Hope you don't mind
	
	// this is kinda bad practice too
	private int vest = 0;
	private int brie = 1;
	private int elixir = 2;
	private int sulfuras = 3;
	private int backstagePass = 4;
	private int cake = 5;
	
	@Test
	public void testGildedRose_Vest() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(vest).getSellIn();
		int quality = GildedRose.getItems().get(vest).getQuality();
		GildedRose.updateQuality();
		assertEquals("Vest SellIn should decrease by 1", GildedRose.getItems().get(vest).getSellIn(), sellIn-1);
		assertEquals("Vest quality should decrease by 1", GildedRose.getItems().get(vest).getQuality(), quality-1);
	}
	
	@Test
	public void testGildedRose_Brie() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(brie).getSellIn();
		int quality = GildedRose.getItems().get(brie).getQuality();
		GildedRose.updateQuality();
		assertEquals("Brie SellIn should decrease by 1", GildedRose.getItems().get(brie).getSellIn(), sellIn-1);
		assertEquals("Brie quality should increase by 1", GildedRose.getItems().get(brie).getQuality(), quality+1);
	}
	
	@Test
	public void testGildedRose_Elixir() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(elixir).getSellIn();
		int quality = GildedRose.getItems().get(elixir).getQuality();
		GildedRose.updateQuality();
		assertEquals("Elixir SellIn should decrease by 1", GildedRose.getItems().get(elixir).getSellIn(), sellIn-1);
		assertEquals("Elixir quality should decrease by 1", GildedRose.getItems().get(elixir).getQuality(), quality-1);
	}
	
	@Test
	public void testGildedRose_BackstagePass() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(backstagePass).getSellIn();
		int quality = GildedRose.getItems().get(backstagePass).getQuality();
		GildedRose.updateQuality();
		assertEquals("Backstage Pass SellIn should decrease by 1", GildedRose.getItems().get(backstagePass).getSellIn(), sellIn-1);
		assertEquals("Backstage Pass quality should inrease by 1", GildedRose.getItems().get(backstagePass).getQuality(), quality+1);
	}
	
	@Test
	public void testGildedRose_Sulfuras() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(sulfuras).getSellIn();
		int quality = GildedRose.getItems().get(sulfuras).getQuality();
		GildedRose.updateQuality();
		assertEquals("Sulfuras SellIn shouldn't decrease (legendary)", GildedRose.getItems().get(sulfuras).getSellIn(), sellIn);
		assertEquals("Sulfuras quality shouldn't decrease (legendary)", GildedRose.getItems().get(sulfuras).getQuality(), quality);
	}
	
	@Test
	public void testGildedRose_Cake() {
		GildedRose.main(null);
		int sellIn = GildedRose.getItems().get(cake).getSellIn();
		int quality = GildedRose.getItems().get(cake).getQuality();
		GildedRose.updateQuality();
		assertEquals("Cake SellIn should decrease by 1", GildedRose.getItems().get(cake).getSellIn(), sellIn-1);
		assertEquals("Cake quality should decrease by 1", GildedRose.getItems().get(cake).getQuality(), quality-1);
	}
	
	@Test
	public void testGildedRose_QualityNeverNegative() {
		GildedRose.main(null);
		// Cake defaults to sell in 3, quality 6
		// Update 7 times
		for (int i = 1; i<=7; i++) {
			GildedRose.updateQuality();
		}
		assertEquals("Item quality shouldn't be negative", GildedRose.getItems().get(cake).getQuality(), 0);		
	}
	
	@Test
	public void testGildedRose_QualityNeverMoreThan50() {
		GildedRose.main(null);
		
		// Brie should be 1 now after one update from main
		assertEquals(GildedRose.getItems().get(brie).getQuality(), 1);
		// Brie value increases by 2 each update
		// (also tests that brie quality increases by +2 each time)
		// Update 30 times
		for (int i = 1; i<=30; i++) {
			GildedRose.updateQuality();
		}
		assertEquals("Item quality shouldn't be over 50", GildedRose.getItems().get(brie).getQuality(), 50);		
	}
	
	@Test
	public void testGildedRose_sellByDatePassedQualityDegradesTwiceAsFast() {
		GildedRose.main(null);
		// Cake defaults to sell in 3, quality 6
		int sellIn = GildedRose.getItems().get(cake).getSellIn();
		
		GildedRose.updateQuality();
		assertEquals("Cake SellIn should decrease by 1", GildedRose.getItems().get(cake).getSellIn(), sellIn-1);
		
		// Update twice
		GildedRose.updateQuality();
		GildedRose.updateQuality();

		// SellIn date should be passed now
		assertTrue(GildedRose.getItems().get(cake).getSellIn() < 0);
		assertEquals(GildedRose.getItems().get(cake).getSellIn(), -1);

		// Should decrease by 2 now
		GildedRose.updateQuality();
		assertEquals("Cake SellIn should decrease by 2", GildedRose.getItems().get(cake).getSellIn(), -2);
	}
	
	@Test
	public void testGildedRose_backstagePassNoValueAfterConcert() {
		GildedRose.main(null);
		// Update 15 times
		for (int i = 1; i<=15; i++) {
			GildedRose.updateQuality();
		}
		assertTrue(GildedRose.getItems().get(backstagePass).getSellIn() <0);
		assertEquals("Backstage pass value should be 0 after concert", GildedRose.getItems().get(backstagePass).getQuality(), 0);
	}
	
	@Test
	public void testGildedRose_backstagePassValueIncreasesBy2WhenSellInLess10() {
		GildedRose.main(null);
		// Update 5 times
		for (int i = 1; i<=5; i++) {
			GildedRose.updateQuality();
		}
		assertTrue(GildedRose.getItems().get(backstagePass).getSellIn() <10);
		int quality = GildedRose.getItems().get(backstagePass).getQuality();
		GildedRose.updateQuality();
		assertEquals("Backstage pass value should increase by 2 when sellIn is less than 10", GildedRose.getItems().get(backstagePass).getQuality(), quality+2);
	}
	
	@Test
	public void testGildedRose_backstagePassValueIncreasesBy3WhenSellInLess5() {
		GildedRose.main(null);
		// Update 10 times
		for (int i = 1; i<=10; i++) {
			GildedRose.updateQuality();
		}
		assertTrue(GildedRose.getItems().get(backstagePass).getSellIn() <5);
		int quality = GildedRose.getItems().get(backstagePass).getQuality();
		GildedRose.updateQuality();
		assertEquals("Backstage pass value should increase by 3 when sellIn is less than 5", GildedRose.getItems().get(backstagePass).getQuality(), quality+3);
	}
	
}
