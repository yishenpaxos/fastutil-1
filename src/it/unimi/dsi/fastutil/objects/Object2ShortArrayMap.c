/* Generic definitions */


#define PACKAGE it.unimi.dsi.fastutil.objects
#define VALUE_PACKAGE it.unimi.dsi.fastutil.shorts
/* Assertions (useful to generate conditional code) */
#unassert keyclass
#assert keyclass(Object)
#unassert keys
 #assert keys(reference)
#unassert valueclass
#assert valueclass(Short)
#unassert values
 #assert values(primitive)
/* Current type and class (and size, if applicable) */
#define KEY_TYPE Object
#define VALUE_TYPE short
#define KEY_CLASS Object
#define VALUE_CLASS Short
#if #keyclass(Object) || #keyclass(Reference)
#define KEY_GENERIC_CLASS K
#define KEY_GENERIC_TYPE K
#define KEY_GENERIC <K>
#define KEY_GENERIC_WILDCARD <?>
#define KEY_EXTENDS_GENERIC <? extends K>
#define KEY_SUPER_GENERIC <? super K>
#define KEY_GENERIC_CAST (K)
#define KEY_GENERIC_ARRAY_CAST (K[])
#define KEY_GENERIC_BIG_ARRAY_CAST (K[][])
#else
#define KEY_GENERIC_CLASS KEY_CLASS
#define KEY_GENERIC_TYPE KEY_TYPE
#define KEY_GENERIC
#define KEY_GENERIC_WILDCARD
#define KEY_EXTENDS_GENERIC
#define KEY_SUPER_GENERIC
#define KEY_GENERIC_CAST
#define KEY_GENERIC_ARRAY_CAST
#define KEY_GENERIC_BIG_ARRAY_CAST
#endif
#if #valueclass(Object) || #valueclass(Reference)
#define VALUE_GENERIC_CLASS V
#define VALUE_GENERIC_TYPE V
#define VALUE_GENERIC <V>
#define VALUE_EXTENDS_GENERIC <? extends V>
#define VALUE_GENERIC_CAST (V)
#define VALUE_GENERIC_ARRAY_CAST (V[])
#else
#define VALUE_GENERIC_CLASS VALUE_CLASS
#define VALUE_GENERIC_TYPE VALUE_TYPE
#define VALUE_GENERIC
#define VALUE_EXTENDS_GENERIC
#define VALUE_GENERIC_CAST
#define VALUE_GENERIC_ARRAY_CAST
#endif
#if #keyclass(Object) || #keyclass(Reference)
#if #valueclass(Object) || #valueclass(Reference)
#define KEY_VALUE_GENERIC <K,V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K, ? extends V>
#else
#define KEY_VALUE_GENERIC <K>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K>
#endif
#else
#if #valueclass(Object) || #valueclass(Reference)
#define KEY_VALUE_GENERIC <V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends V>
#else
#define KEY_VALUE_GENERIC
#define KEY_VALUE_EXTENDS_GENERIC
#endif
#endif
/* Value methods */
#define KEY_VALUE ObjectValue
#define VALUE_VALUE shortValue
/* Interfaces (keys) */
#define COLLECTION ObjectCollection

#define SET ObjectSet

#define HASH ObjectHash

#define SORTED_SET ObjectSortedSet

#define STD_SORTED_SET SortedSet

#define FUNCTION Object2ShortFunction
#define MAP Object2ShortMap
#define SORTED_MAP Object2ShortSortedMap
#if #keyclass(Object) || #keyclass(Reference)
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Object2ShortSortedMap

#define STRATEGY PACKAGE.ObjectHash.Strategy

#endif
#define LIST ObjectList

#define BIG_LIST ObjectBigList

#define STACK Stack

#define PRIORITY_QUEUE PriorityQueue

#define INDIRECT_PRIORITY_QUEUE IndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE IndirectDoublePriorityQueue

#define KEY_ITERATOR ObjectIterator

#define KEY_ITERABLE ObjectIterable

#define KEY_BIDI_ITERATOR ObjectBidirectionalIterator

#define KEY_LIST_ITERATOR ObjectListIterator

#define KEY_BIG_LIST_ITERATOR ObjectBigListIterator

#define STD_KEY_ITERATOR Iterator

#define KEY_COMPARATOR Comparator

/* Interfaces (values) */
#define VALUE_COLLECTION ShortCollection

#define VALUE_ARRAY_SET ShortArraySet

#define VALUE_ITERATOR ShortIterator

#define VALUE_LIST_ITERATOR ShortListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractObjectCollection

#define ABSTRACT_SET AbstractObjectSet

#define ABSTRACT_SORTED_SET AbstractObjectSortedSet
#define ABSTRACT_FUNCTION AbstractObject2ShortFunction
#define ABSTRACT_MAP AbstractObject2ShortMap
#define ABSTRACT_FUNCTION AbstractObject2ShortFunction
#define ABSTRACT_SORTED_MAP AbstractObject2ShortSortedMap
#define ABSTRACT_LIST AbstractObjectList

#define ABSTRACT_BIG_LIST AbstractObjectBigList

#define SUBLIST ObjectSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractPriorityQueue

#define ABSTRACT_STACK AbstractStack

#define KEY_ABSTRACT_ITERATOR AbstractObjectIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractObjectBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractObjectListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractObjectBigListIterator

#if #keyclass(Object)
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractObjectComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractShortCollection

#define VALUE_ABSTRACT_ITERATOR AbstractShortIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractShortBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS ObjectCollections

#define SETS ObjectSets

#define SORTED_SETS ObjectSortedSets

#define LISTS ObjectLists

#define BIG_LISTS ObjectBigLists

#define MAPS Object2ShortMaps
#define FUNCTIONS Object2ShortFunctions
#define SORTED_MAPS Object2ShortSortedMaps
#define PRIORITY_QUEUES ObjectPriorityQueues

#define HEAPS ObjectHeaps

#define SEMI_INDIRECT_HEAPS ObjectSemiIndirectHeaps

#define INDIRECT_HEAPS ObjectIndirectHeaps

#define ARRAYS ObjectArrays

#define BIG_ARRAYS ObjectBigArrays

#define ITERATORS ObjectIterators

#define BIG_LIST_ITERATORS ObjectBigListIterators

#define COMPARATORS ObjectComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS ShortCollections

#define VALUE_SETS ShortSets

#define VALUE_ARRAYS ShortArrays

/* Implementations */
#define OPEN_HASH_SET ObjectOpenHashSet

#define OPEN_HASH_BIG_SET ObjectOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET ObjectOpenDoubleHashSet

#define OPEN_HASH_MAP Object2ShortOpenHashMap

#define STRIPED_OPEN_HASH_MAP StripedObject2ShortOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Object2ShortOpenDoubleHashMap

#define ARRAY_SET ObjectArraySet

#define ARRAY_MAP Object2ShortArrayMap

#define LINKED_OPEN_HASH_SET ObjectLinkedOpenHashSet

#define AVL_TREE_SET ObjectAVLTreeSet

#define RB_TREE_SET ObjectRBTreeSet

#define AVL_TREE_MAP Object2ShortAVLTreeMap

#define RB_TREE_MAP Object2ShortRBTreeMap

#define ARRAY_LIST ObjectArrayList

#define BIG_ARRAY_BIG_LIST ObjectBigArrayBigList

#define ARRAY_FRONT_CODED_LIST ObjectArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE ObjectHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE ObjectHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE ObjectHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE ObjectArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE ObjectArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE ObjectArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE ObjectArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedObjectCollection

#define SYNCHRONIZED_SET SynchronizedObjectSet

#define SYNCHRONIZED_SORTED_SET SynchronizedObjectSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedObject2ShortFunction

#define SYNCHRONIZED_MAP SynchronizedObject2ShortMap

#define SYNCHRONIZED_LIST SynchronizedObjectList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableObjectCollection

#define UNMODIFIABLE_SET UnmodifiableObjectSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableObjectSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableObject2ShortFunction

#define UNMODIFIABLE_MAP UnmodifiableObject2ShortMap

#define UNMODIFIABLE_LIST UnmodifiableObjectList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableObjectIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableObjectBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableObjectListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER ObjectReaderWrapper

#define KEY_DATA_INPUT_WRAPPER ObjectDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY next
#define PREV_KEY previous
#define FIRST_KEY firstKey
#define LAST_KEY lastKey
#define GET_KEY get
#define REMOVE_KEY remove
#define READ_KEY readObject
#define WRITE_KEY writeObject
#define DEQUEUE dequeue
#define DEQUEUE_LAST dequeueLast
#define SUBLIST_METHOD objectSubList
#define SINGLETON_METHOD objectSingleton

#define FIRST first
#define LAST last
#define TOP top
#define PEEK peek
#define POP pop
#define KEY_ITERATOR_METHOD objectIterator

#define KEY_LIST_ITERATOR_METHOD objectListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyObjectIterator

#define AS_KEY_ITERATOR asObjectIterator

#define TO_KEY_ARRAY toArray
#define ENTRY_GET_KEY getKey
#define REMOVE_FIRST_KEY removeFirst
#define REMOVE_LAST_KEY removeLast
#define PARSE_KEY parse
#define LOAD_KEYS loads
#define LOAD_KEYS_BIG loadsBig
#define STORE_KEYS stores
/* Methods (values) */
#define NEXT_VALUE nextShort
#define PREV_VALUE previousShort
#define READ_VALUE readShort
#define WRITE_VALUE writeShort
#define VALUE_ITERATOR_METHOD shortIterator

#define ENTRY_GET_VALUE getShortValue
#define REMOVE_FIRST_VALUE removeFirstShort
#define REMOVE_LAST_VALUE removeLastShort
/* Methods (keys/values) */
#define ENTRYSET object2ShortEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if #keyclass(Object) || #keyclass(Reference)
#define GET_VALUE getShort
#define REMOVE_VALUE removeShort
#else
#define GET_VALUE get
#define REMOVE_VALUE remove
#endif
/* Equality */
#ifdef Custom
#define KEY_EQUALS(x,y) ( strategy.equals( (x),  KEY_GENERIC_CAST (y) ) )
#else
#if #keyclass(Object)
#define KEY_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x).equals(y) )
#else
#define KEY_EQUALS(x,y) ( (x) == (y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x) == (y) )
#endif
#endif

#if #valueclass(Object)
#define VALUE_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#else
#define VALUE_EQUALS(x,y) ( (x) == (y) )
#endif

/* Object/Reference-only definitions (keys) */
#if #keyclass(Object) || #keyclass(Reference)
#define REMOVE remove
#define KEY_OBJ2TYPE(x) (x)
#define KEY_CLASS2TYPE(x) (x)
#define KEY2OBJ(x) (x)
#if #keyclass(Object)
#ifdef Custom
#define KEY2JAVAHASH(x) ( strategy.hashCode( KEY_GENERIC_CAST (x)) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( strategy.hashCode( KEY_GENERIC_CAST (x)) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)strategy.hashCode( KEY_GENERIC_CAST (x)) ) )
#else
#define KEY2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#define KEY2INTHASH(x) ( (x) == null ? 0x87fcd5c : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (x).hashCode() ) )
#define KEY2LONGHASH(x) ( (x) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)(x).hashCode() ) )
#endif
#else
#define KEY2JAVAHASH(x) ( (x) == null ? 0 : System.identityHashCode(x) )
#define KEY2INTHASH(x) ( (x) == null ? 0x87fcd5c : it.unimi.dsi.fastutil.HashCommon.murmurHash3( System.identityHashCode(x) ) )
#define KEY2LONGHASH(x) ( (x) == null ? 0x810879608e4259ccL : it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)System.identityHashCode(x) ) )
#endif
#define KEY_CMP(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) )
#define KEY_CMP_EQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) == 0 )
#define KEY_LESS(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) < 0 )
#define KEY_LESSEQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) <= 0 )
#define KEY_NULL (null)
#else
/* Primitive-type-only definitions (keys) */
#define REMOVE rem
#define KEY_CLASS2TYPE(x) ((x).KEY_VALUE())
#define KEY_OBJ2TYPE(x) (KEY_CLASS2TYPE((KEY_CLASS)(x)))
#define KEY2OBJ(x) (KEY_CLASS.valueOf(x))
#if #keyclass(Boolean)
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_NULL (false)
#define KEY_CMP(x,y) ( !(x) && (y) ? -1 : ( (x) == (y) ? 0 : 1 ) )
#define KEY_LESS(x,y) ( !(x) && (y) )
#define KEY_LESSEQ(x,y) ( !(x) || (y) )
#else
#define KEY_NULL ((KEY_TYPE)0)
#if #keyclass(Float) || #keyclass(Double)
#define KEY_CMP_EQ(x,y) ( KEY_CLASS.compare((x),(y)) == 0 )
#define KEY_CMP(x,y) ( KEY_CLASS.compare((x),(y)) )
#define KEY_LESS(x,y) ( KEY_CLASS.compare((x),(y)) < 0 )
#define KEY_LESSEQ(x,y) ( KEY_CLASS.compare((x),(y)) <= 0 )
#else
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_CMP(x,y) ( (x) < (y) ? -1 : ( (x) == (y) ? 0 : 1 ) )
#define KEY_LESS(x,y) ( (x) < (y) )
#define KEY_LESSEQ(x,y) ( (x) <= (y) )
#endif
#if #keyclass(Float)
#define KEY2LEXINT(x) fixFloat(x)
#elif #keyclass(Double)
#define KEY2LEXINT(x) fixDouble(x)
#else
#define KEY2LEXINT(x) (x)
#endif
#endif
#ifdef Custom
#define KEY2JAVAHASH(x) ( strategy.hashCode(x) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( strategy.hashCode(x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)strategy.hashCode(x) ) )
#else
#if #keyclass(Float)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.float2int(x)
#define KEY2INTHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3( it.unimi.dsi.fastutil.HashCommon.float2int(x) )
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)it.unimi.dsi.fastutil.HashCommon.float2int(x) )
#elif #keyclass(Double)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.double2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.murmurHash3(Double.doubleToRawLongBits(x))
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3(Double.doubleToRawLongBits(x))
#elif #keyclass(Long)
#define KEY2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.long2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.murmurHash3(x)
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.murmurHash3(x)
#elif #keyclass(Boolean)
#define KEY2JAVAHASH(x) ((x) ? 1231 : 1237)
#define KEY2INTHASH(x) ((x) ? 0xfab5368 : 0xcba05e7b)
#define KEY2LONGHASH(x) ((x) ? 0x74a19fc8b6428188L : 0xbaeca2031a4fd9ecL)
#else
#define KEY2JAVAHASH(x) (x)
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.murmurHash3( (long)(x) ) )
#endif
#endif
#endif
/* Object/Reference-only definitions (values) */
#if #valueclass(Object) || #valueclass(Reference)
#define VALUE_OBJ2TYPE(x) (x)
#define VALUE_CLASS2TYPE(x) (x)
#define VALUE2OBJ(x) (x)
#if #valueclass(Object)
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#else
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : System.identityHashCode(x) )
#endif
#define VALUE_NULL (null)
#define OBJECT_DEFAULT_RETURN_VALUE (this.defRetValue)
#else
/* Primitive-type-only definitions (values) */
#define VALUE_CLASS2TYPE(x) ((x).VALUE_VALUE())
#define VALUE_OBJ2TYPE(x) (VALUE_CLASS2TYPE((VALUE_CLASS)(x)))
#define VALUE2OBJ(x) (VALUE_CLASS.valueOf(x))
#if #valueclass(Float) || #valueclass(Double) || #valueclass(Long)
#define VALUE_NULL (0)
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.short2int(x)
#elif #valueclass(Boolean)
#define VALUE_NULL (false)
#define VALUE2JAVAHASH(x) (x ? 1231 : 1237)
#else
#if #valueclass(Integer)
#define VALUE_NULL (0)
#else
#define VALUE_NULL ((VALUE_TYPE)0)
#endif
#define VALUE2JAVAHASH(x) (x)
#endif
#define OBJECT_DEFAULT_RETURN_VALUE (null)
#endif
#include "drv/ArrayMap.drv"

