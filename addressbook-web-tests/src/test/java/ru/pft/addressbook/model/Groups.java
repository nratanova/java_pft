package ru.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Наташа on 08.03.2017.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  //Метод для создания копии, в которую добавлена группа
  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  //Метод для создания копии, откуда удалена группа
  public Groups without(GroupData group) {
    Groups groups = new Groups(this);//копия существующего объекта
    groups.remove(group);
    return groups;
  }
}
