package com.example.hjhan.testhhj.stickyHeaderTest.sticky;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.hjhan.testhhj.R;
import com.example.hjhan.testhhj.stickyHeaderTest.sticky.model.BoardListItem;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Adapter for Person items. Sorts them by last name into sections starting with the
 * first letter of the last name.
 */
public class DataSetAdapter extends SectioningAdapter {

	Locale locale = Locale.getDefault();
	static final boolean USE_DEBUG_APPEARANCE = false;

	final int ITEM_TYPE_TEXT = 0;
	final int ITEM_TYPE_IMAGE = 1;

	private class Section {
		String alpha;
		ArrayList<BoardListItem> sectionBoardListItemList = new ArrayList<>();
	}


	public class ItemViewHolder1 extends SectioningAdapter.ItemViewHolder {
		TextView personNameTextView;

		public ItemViewHolder1(View itemView) {
			super(itemView);
			personNameTextView = (TextView) itemView.findViewById(R.id.personNameTextView);
		}
	}


	public class ItemViewHolder2 extends SectioningAdapter.ItemViewHolder {
		TextView personNameTextView;

		public ItemViewHolder2(View itemView) {
			super(itemView);
			personNameTextView = (TextView) itemView.findViewById(R.id.personNameTextView);
		}
	}


	public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
		TextView personNameTextView;

		public ItemViewHolder(View itemView) {
			super(itemView);
			personNameTextView = (TextView) itemView.findViewById(R.id.personNameTextView);
		}
	}

	public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
		TextView titleTextView;

		public HeaderViewHolder(View itemView) {
			super(itemView);
			titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
		}
	}


	List<BoardListItem> boardListItemList;
	ArrayList<Section> sections = new ArrayList<>();

	public DataSetAdapter() {
	}

	public List<BoardListItem> getPeople() {
		return boardListItemList;
	}

	public void setPeople(List<BoardListItem> people) {
		this.boardListItemList = people;
		sections.clear();

		// sort people into buckets by the first letter of last name
		long sectionIdx = -1;
		Section currentSection = null;
		for (BoardListItem boardItem : people) {
			if (boardItem.sectionIdx != sectionIdx) {
				if (currentSection != null) {
					sections.add(currentSection);
				}

				currentSection = new Section();
				sectionIdx = boardItem.sectionIdx;
				currentSection.alpha = boardItem.sectionTitle;
			}

			if (currentSection != null) {
			//	currentSection.sectionBoardListItemList.add(boardItem);
			}
		}

		sections.add(currentSection);
		notifyAllSectionsDataSetChanged();
	}

	@Override
	public int getNumberOfSections() {
		return sections.size();
	}

	@Override
	public int getNumberOfItemsInSection(int sectionIndex) {
		return sections.get(sectionIndex).sectionBoardListItemList.size();
	}

	@Override
	public boolean doesSectionHaveHeader(int sectionIndex) {
		return true;
	}

	@Override
	public boolean doesSectionHaveFooter(int sectionIndex) {
		return false;
	}

	@Override
	public SectioningAdapter.ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		if(itemType == ITEM_TYPE_TEXT){

			View v = inflater.inflate(R.layout.list_item_addressbook_person, parent, false);
			return new ItemViewHolder1(v);
		}
		else{
			View v = inflater.inflate(R.layout.list_item_addressbook_img, parent, false);
			return new ItemViewHolder2(v);
		}
	}

	@Override
	public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.list_item_addressbook_header, parent, false);
		return new HeaderViewHolder(v);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
		Section s = sections.get(sectionIndex);
		BoardListItem person = s.sectionBoardListItemList.get(itemIndex);
		if(itemType == ITEM_TYPE_TEXT){
			ItemViewHolder1 ivh = (ItemViewHolder1) viewHolder;
			ivh.personNameTextView.setText(person.boardName);
		}
		else{
			ItemViewHolder2 ivh2 = (ItemViewHolder2) viewHolder;
			ivh2.personNameTextView.append(person.boardName);
		}
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
		Section s = sections.get(sectionIndex);
		HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;

		if (USE_DEBUG_APPEARANCE) {
			hvh.itemView.setBackgroundColor(0x55ffffff);
			hvh.titleTextView.setText(pad(sectionIndex * 2) + s.alpha);
		} else {
			hvh.titleTextView.setText(s.alpha);
		}
	}

	private String capitalize(String s) {
		if (s != null && s.length() > 0) {
			return s.substring(0,1).toUpperCase(locale) + s.substring(1);
		}

		return "";
	}

	private String pad(int spaces) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < spaces; i++) {
			b.append(' ');
		}
		return b.toString();
	}


	public int getSectionItemUserType(int sectionIndex, int itemIndex) {
		if(boardListItemList.get(itemIndex).cardType.equals("text"))
			return  ITEM_TYPE_TEXT;
		else
			return  ITEM_TYPE_IMAGE;

	}


}
