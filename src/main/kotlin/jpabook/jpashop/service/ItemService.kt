package jpabook.jpashop.service

import jpabook.jpashop.domain.Item
import jpabook.jpashop.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(val itemRepository: ItemRepository) {


    @Transactional
    fun saveItem(item: Item) {
        return itemRepository.save(item)
    }

    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    fun findOne(id: Long): Item? {
        return itemRepository.findOne(id)
    }

}