#define SET_PREV( f64, p32 )       SET_UPPER( f64, p32 )
#define SET_NEXT( f64, n32 )       SET_LOWER( f64, n32 )
#define COPY_PREV( f64, p64 )      SET_UPPER64( f64, p64 )
#define COPY_NEXT( f64, n64 )      SET_LOWER64( f64, n64 )
#define GET_PREV( f64 )            GET_UPPER( f64 )
#define GET_NEXT( f64 )            GET_LOWER( f64 )
#define SET_UPPER_LOWER( f64, up32, low32 )    f64 = ( ( up32 & 0xFFFFFFFFL ) << 32 ) | ( low32 & 0xFFFFFFFFL )
#define SET_UPPER( f64, up32 )     f64 ^= ( ( f64 ^ ( ( up32 & 0xFFFFFFFFL ) << 32 ) ) & 0xFFFFFFFF00000000L )
#define SET_LOWER( f64, low32 )    f64 ^= ( ( f64 ^ ( low32 & 0xFFFFFFFFL ) ) & 0xFFFFFFFFL )
#define SET_UPPER64( f64, up64 )   f64 ^= ( ( f64 ^ ( up64 & 0xFFFFFFFF00000000L ) ) & 0xFFFFFFFF00000000L )
#define SET_LOWER64( f64, low64 )  f64 ^= ( ( f64 ^ ( low64 & 0xFFFFFFFFL ) ) & 0xFFFFFFFFL )
#define GET_UPPER( f64 )           (int) ( f64 >>> 32 )
#define GET_LOWER( f64 )           (int) f64

/* Generic definitions */
#define Linked

#define PACKAGE it.unimi.dsi.fastutil.shorts
#define VALUE_PACKAGE it.unimi.dsi.fastutil.booleans
/* Assertions (useful to generate conditional code) */
#unassert keyclass
#assert keyclass(Short)
#unassert keys
 #assert keys(primitive)
#unassert valueclass
#assert valueclass(Boolean)
#unassert values
 #assert values(primitive)
/* Current type and class (and size, if applicable) */
#define KEY_TYPE short
#define VALUE_TYPE boolean
#define KEY_CLASS Short
#define VALUE_CLASS Boolean
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
#define KEY_VALUE shortValue
#define VALUE_VALUE booleanValue
/* Interfaces (keys) */
#define COLLECTION ShortCollection

#define SET ShortSet

#define HASH ShortHash

#define SORTED_SET ShortSortedSet

#define STD_SORTED_SET ShortSortedSet

#define FUNCTION Short2BooleanFunction
#define MAP Short2BooleanMap
#define SORTED_MAP Short2BooleanSortedMap
#if #keyclass(Object) || #keyclass(Reference)
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Short2BooleanSortedMap

#define STRATEGY PACKAGE.ShortHash.Strategy

#endif
#define LIST ShortList

#define BIG_LIST ShortBigList

#define STACK ShortStack

#define PRIORITY_QUEUE ShortPriorityQueue

#define INDIRECT_PRIORITY_QUEUE ShortIndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE ShortIndirectDoublePriorityQueue

#define KEY_ITERATOR ShortIterator

#define KEY_ITERABLE ShortIterable

#define KEY_BIDI_ITERATOR ShortBidirectionalIterator

#define KEY_LIST_ITERATOR ShortListIterator

#define KEY_BIG_LIST_ITERATOR ShortBigListIterator

#define STD_KEY_ITERATOR ShortIterator

#define KEY_COMPARATOR ShortComparator

/* Interfaces (values) */
#define VALUE_COLLECTION BooleanCollection

#define VALUE_ARRAY_SET BooleanArraySet

#define VALUE_ITERATOR BooleanIterator

#define VALUE_LIST_ITERATOR BooleanListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractShortCollection

#define ABSTRACT_SET AbstractShortSet

#define ABSTRACT_SORTED_SET AbstractShortSortedSet
#define ABSTRACT_FUNCTION AbstractShort2BooleanFunction
#define ABSTRACT_MAP AbstractShort2BooleanMap
#define ABSTRACT_FUNCTION AbstractShort2BooleanFunction
#define ABSTRACT_SORTED_MAP AbstractShort2BooleanSortedMap
#define ABSTRACT_LIST AbstractShortList

#define ABSTRACT_BIG_LIST AbstractShortBigList

#define SUBLIST ShortSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractShortPriorityQueue

#define ABSTRACT_STACK AbstractShortStack

#define KEY_ABSTRACT_ITERATOR AbstractShortIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractShortBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractShortListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractShortBigListIterator

#if #keyclass(Object)
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractShortComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractBooleanCollection

#define VALUE_ABSTRACT_ITERATOR AbstractBooleanIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractBooleanBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS ShortCollections

#define SETS ShortSets

#define SORTED_SETS ShortSortedSets

#define LISTS ShortLists

#define BIG_LISTS ShortBigLists

#define MAPS Short2BooleanMaps
#define FUNCTIONS Short2BooleanFunctions
#define SORTED_MAPS Short2BooleanSortedMaps
#define PRIORITY_QUEUES ShortPriorityQueues

#define HEAPS ShortHeaps

#define SEMI_INDIRECT_HEAPS ShortSemiIndirectHeaps

#define INDIRECT_HEAPS ShortIndirectHeaps

#define ARRAYS ShortArrays

#define BIG_ARRAYS ShortBigArrays

#define ITERATORS ShortIterators

#define BIG_LIST_ITERATORS ShortBigListIterators

#define COMPARATORS ShortComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS BooleanCollections

#define VALUE_SETS BooleanSets

#define VALUE_ARRAYS BooleanArrays

/* Implementations */
#define OPEN_HASH_SET ShortLinkedOpenHashSet

#define OPEN_HASH_BIG_SET ShortLinkedOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET ShortLinkedOpenDoubleHashSet

#define OPEN_HASH_MAP Short2BooleanLinkedOpenHashMap

#define STRIPED_OPEN_HASH_MAP StripedShort2BooleanOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Short2BooleanLinkedOpenDoubleHashMap

#define ARRAY_SET ShortArraySet

#define ARRAY_MAP Short2BooleanArrayMap

#define LINKED_OPEN_HASH_SET ShortLinkedOpenHashSet

#define AVL_TREE_SET ShortAVLTreeSet

#define RB_TREE_SET ShortRBTreeSet

#define AVL_TREE_MAP Short2BooleanAVLTreeMap

#define RB_TREE_MAP Short2BooleanRBTreeMap

#define ARRAY_LIST ShortArrayList

#define BIG_ARRAY_BIG_LIST ShortBigArrayBigList

#define ARRAY_FRONT_CODED_LIST ShortArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE ShortHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE ShortHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE ShortHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE ShortHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE ShortHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE ShortArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE ShortArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE ShortArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE ShortArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedShortCollection

#define SYNCHRONIZED_SET SynchronizedShortSet

#define SYNCHRONIZED_SORTED_SET SynchronizedShortSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedShort2BooleanFunction

#define SYNCHRONIZED_MAP SynchronizedShort2BooleanMap

#define SYNCHRONIZED_LIST SynchronizedShortList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableShortCollection

#define UNMODIFIABLE_SET UnmodifiableShortSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableShortSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableShort2BooleanFunction

#define UNMODIFIABLE_MAP UnmodifiableShort2BooleanMap

#define UNMODIFIABLE_LIST UnmodifiableShortList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableShortIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableShortBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableShortListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER ShortReaderWrapper

#define KEY_DATA_INPUT_WRAPPER ShortDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY nextShort
#define PREV_KEY previousShort
#define FIRST_KEY firstShortKey
#define LAST_KEY lastShortKey
#define GET_KEY getShort
#define REMOVE_KEY removeShort
#define READ_KEY readShort
#define WRITE_KEY writeShort
#define DEQUEUE dequeueShort
#define DEQUEUE_LAST dequeueLastShort
#define SUBLIST_METHOD shortSubList
#define SINGLETON_METHOD shortSingleton

#define FIRST firstShort
#define LAST lastShort
#define TOP topShort
#define PEEK peekShort
#define POP popShort
#define KEY_ITERATOR_METHOD shortIterator

#define KEY_LIST_ITERATOR_METHOD shortListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyShortIterator

#define AS_KEY_ITERATOR asShortIterator

#define TO_KEY_ARRAY toShortArray
#define ENTRY_GET_KEY getShortKey
#define REMOVE_FIRST_KEY removeFirstShort
#define REMOVE_LAST_KEY removeLastShort
#define PARSE_KEY parseShort
#define LOAD_KEYS loadShorts
#define LOAD_KEYS_BIG loadShortsBig
#define STORE_KEYS storeShorts
/* Methods (values) */
#define NEXT_VALUE nextBoolean
#define PREV_VALUE previousBoolean
#define READ_VALUE readBoolean
#define WRITE_VALUE writeBoolean
#define VALUE_ITERATOR_METHOD booleanIterator

#define ENTRY_GET_VALUE getBooleanValue
#define REMOVE_FIRST_VALUE removeFirstBoolean
#define REMOVE_LAST_VALUE removeLastBoolean
/* Methods (keys/values) */
#define ENTRYSET short2BooleanEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if #keyclass(Object) || #keyclass(Reference)
#define GET_VALUE getBoolean
#define REMOVE_VALUE removeBoolean
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
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.boolean2int(x)
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
#include "drv/LinkedOpenHashMap.drv"

